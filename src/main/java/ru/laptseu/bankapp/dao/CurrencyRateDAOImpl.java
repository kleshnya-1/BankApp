package ru.laptseu.bankapp.dao;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.CustomDocument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo проблема с имплиментированием. метод read(int) дальше описание
@Log4j2
@Repository
@RequiredArgsConstructor
public class CurrencyRateDAOImpl   // implements  IMaintainableDAO<CurrencyRate>
{

   @NonNull
   private CurrRateRepoMongoExtends currRateRepoMongoExtends;

    public int save(CurrencyRate obj) {
        Bank savedBank = obj.getBank();
        //todo replace to INteger
        if (obj.getBank() == null & obj.getBankId() == 0)
            throw new NullPointerException("Can't save CurrencyRate without bank ID");
        //todo replace type of exception
        if (obj.getBank() != null && obj.getBankId() != 0 && obj.getBank().getId() != obj.getBankId())
            throw new RuntimeException("BankID from field and entity unequal " + obj.getBankId() + "!=" + obj.getBank().getId());

        if (obj.getBankId() == 0) {
            obj.setBankId(obj.getBank().getId());
        }
        //manual @Transient иначе цикличная зависимость и лишние поля в БСОНе
        obj.setBank(null);

        CustomDocument document = currRateRepoMongoExtends.findByBankId(obj.getBankId());
        if (document == null) {
            document = new CustomDocument();
            List<CurrencyRate> list = new ArrayList<>();
            list.add(obj);
            document.setCurrencies(list);
            document.setBankId(obj.getBankId());
        } else {
            document.getCurrencies().add(obj);
        }
        currRateRepoMongoExtends.save(document);

        //todo ask как-то так. так сущность сохранится без него,
        // но оставнийся объект может нормально функционировать
        obj.setBank(savedBank);
        //todo fix return
        return 1;
    }

    public int save(List<CurrencyRate> list) {
        //сохранять можно и сразу, но все равно придется перебрать весь массив и
        // удалить банки. потому так
        //todo stream and return
        for (CurrencyRate cr : list) {
            save(cr);
        }
        return 1;
    }

    //todo в интерфейсе возврящается один обънет. а у меня лист. в связи с
    // этим: либо в остальные классы заглушку -- либо не имплиментировать. а как лучше?
    // (сюда я даю ИД банка. и получаю лист его курсов. интерфейс не понимает,
    // так как он привык получать ИД сущности и ее возвращать (дженерик))
    public List<CurrencyRate> read(int key) {
        // Bank bankOwner = bankDAO.read(key);
        //todo null exc
        CustomDocument o3 = currRateRepoMongoExtends.findByBankId(key);
        //todo check with null
        List<CurrencyRate> rates;
        System.out.println(o3);
        if (o3 != null && o3.getCurrencies() != null) {
            rates = o3.getCurrencies();
            rates.stream().forEach(currencyRate -> currencyRate.setBankId(key));
        } else rates = new ArrayList<>();
        return rates;
    }

    public CurrencyRate read(int key, Currency c) {
        List<CurrencyRate> reversed = read(key);
        Collections.reverse(reversed);
        return reversed.stream().filter(cr -> cr.getCurrency().equals(c)).findFirst().orElse(null);
    }
}
