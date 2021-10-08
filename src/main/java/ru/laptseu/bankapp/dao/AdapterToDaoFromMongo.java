package ru.laptseu.bankapp.dao;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateList;
import ru.laptseu.bankapp.repositories.CurrencyRateRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdapterToDaoFromMongo implements CrudRepository<BankRateList, ObjectId> {
    private final CurrencyRateRepository dao;


    public BankRateList save(BankRateList obj) {
        return dao.save(obj);
    }


    public BankRateList read(int id) {
        return dao.findById(id);
    }


    public void update(BankRateList obj) {
        dao.save(obj);
    }

    @Override
    public void delete(BankRateList obj) {
        dao.delete(obj);
    }

    @Override
    public <S extends BankRateList> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<BankRateList> findById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(ObjectId objectId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<BankRateList> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<BankRateList> findAllById(Iterable<ObjectId> iterable) {
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
    public void deleteAll(Iterable<? extends BankRateList> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
