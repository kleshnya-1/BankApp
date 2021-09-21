package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Currency;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class CurrencyRateServiceForTest {

    private final Set<BankRateListDocument> dao;

    public BankRateListDocument save(BankRateListDocument obj) {
       obj.setDate(Calendar.getInstance().getTime());
       dao.add(obj);
        return  obj;
    }


    public BankRateListDocument read(int key)  {
          return dao.stream().filter(i -> i.getBankId()==key).sorted(Comparator.comparing(o->o.getDate())).findFirst().orElse(null);

    }

    public Double read(int key, Currency c) {
        return read(key).getCurrenciesAndRates().get(c);
    }

    public void delete(BankRateListDocument cd1) {
        dao.remove(cd1);
    }
}
