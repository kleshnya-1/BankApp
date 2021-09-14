package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.CurrRateDocumentsDAO;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.BankRateListDocument;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
//todo in progress. not for checking
public class CurrencyRateService //implements IMaintainableService<CurrencyRate>
{

    private final CurrRateDocumentsDAO bankRateListDocumentDAO;


    public int save(CurrencyRate obj) throws Throwable {
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
        BankRateListDocument document =null;
        try {
            document = bankRateListDocumentDAO.readByBankId(obj.getBankId());
        } catch (EntityNotFoundException e){
            log.error(e);
        }

        if (document == null) {
            document = new BankRateListDocument();
            List<CurrencyRate> list = new ArrayList<>();
            list.add(obj);
            document.setCurrencies(list);
            document.setBankId(obj.getBankId());
        } else {
            document.getCurrencies().add(obj);
        }
        bankRateListDocumentDAO.save(document);

        //todo ask как-то так. так сущность сохранится без него,
        // но оставнийся объект может нормально функционировать
        obj.setBank(savedBank);
        //todo fix return
        return 1;
    }

    public int save(List<CurrencyRate> list) throws Throwable {
        //сохранять можно и сразу, но все равно придется перебрать весь массив и
        // удалить банки. потому так
        //todo stream and return
        for (CurrencyRate cr : list) {
            save(cr);
        }
        return 1;
    }

    //todo fix

    public List<CurrencyRate> read(int key) throws Throwable {
        // Bank bankOwner = bankDAO.read(key);
        //todo null exc
        BankRateListDocument o3 = bankRateListDocumentDAO.readByBankId(key);
        //todo check with null
        List<CurrencyRate> rates;
        System.out.println(o3);
        if (o3 != null && o3.getCurrencies() != null) {
            rates = o3.getCurrencies();
            rates.stream().forEach(currencyRate -> currencyRate.setBankId(key));
        } else rates = new ArrayList<>();
        return rates;
    }


    public CurrencyRate read(int key, Currency c) throws Throwable {
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
