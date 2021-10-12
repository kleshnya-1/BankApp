package ru.laptseu.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.CurrencyRateBankList;
import ru.laptseu.bankapp.repositories.CurrencyRateRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdapterToDaoFromMongo implements CrudRepository<CurrencyRateBankList, ObjectId> {
    private final CurrencyRateRepository dao;


    public CurrencyRateBankList save(CurrencyRateBankList obj) {
        return dao.save(obj);
    }


    public CurrencyRateBankList read(int id) {
        return dao.findById(id);
    }


    public void update(CurrencyRateBankList obj) {
        dao.save(obj);
    }

    @Override
    public void delete(CurrencyRateBankList obj) {
        dao.delete(obj);
    }

    @Override
    public <S extends CurrencyRateBankList> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CurrencyRateBankList> findById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<CurrencyRateBankList> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<CurrencyRateBankList> findAllById(Iterable<ObjectId> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends ObjectId> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends CurrencyRateBankList> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
