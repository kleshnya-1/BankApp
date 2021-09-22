package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Currency;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class CurrencyRateServiceForTest {

    private final List<BankRateListDocument> dao;

    public BankRateListDocument save(BankRateListDocument obj) {
       obj.setDate(Calendar.getInstance().getTime());
        obj.setId(new ObjectId());
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
    public void delete(int key) {
        dao.remove(read(key));
    }
}
