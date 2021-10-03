package ru.laptseu.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.repositories.CurrencyRateRepository;

@Repository
@RequiredArgsConstructor
public class AdapterToDaoFromMongo extends AbstractDao<BankRateListDocument> {
    //adapter demo class.
    private final CurrencyRateRepository dao;

    @Override
    public BankRateListDocument save(BankRateListDocument obj) {
        return dao.save(obj);
    }

    @Override
    public BankRateListDocument read(int id) {
        return  dao.findById(id);
    }

    @Override
    public void update(BankRateListDocument obj) {
        dao.save(obj);
    }

    @Override
    public void delete(BankRateListDocument obj) {
        dao.delete(obj);
    }
}
