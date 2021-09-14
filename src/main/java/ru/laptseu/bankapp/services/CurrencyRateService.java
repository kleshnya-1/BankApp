package ru.laptseu.bankapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.CurrRateRepoMongoExtends;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.CustomDocument;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
//todo in progress. not for checking
public class CurrencyRateService //implements IMaintainableService<CurrencyRate>
{

    //todo  getLastCurrency() ref
    @Autowired
    CurrRateRepoMongoExtends currencyRateDao;

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

        CustomDocument document = currencyRateDao.findByBankId(obj.getBankId());
        if (document == null) {
            document = new CustomDocument();
            List<CurrencyRate> list = new ArrayList<>();
            list.add(obj);
            document.setCurrencies(list);
            document.setBankId(obj.getBankId());
        } else {
            document.getCurrencies().add(obj);
        }
        currencyRateDao.save(document);

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

    //todo fix

    public List<CurrencyRate> read(int key) {
        // Bank bankOwner = bankDAO.read(key);
        //todo null exc
        CustomDocument o3 = currencyRateDao.findByBankId(key);
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

    public void update(CurrencyRate currencyRate) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void delete(int key) throws SQLException {
    }
}
