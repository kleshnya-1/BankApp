package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.EntitySuperModel;

public interface IMaintainableDAO<T extends EntitySuperModel> {
    T getEntity();

    CrudRepository getRep();

    default T save(T obj) {
        return (T) getRep().save(obj);
    }

    default T read(int id) throws Throwable {
        return (T) getRep().findById(id).orElseThrow(() -> new EntityNotFoundException(getEntity().getClass() + " with ID " + id + " not found"));
    }

    //так как читаем из монго мы не по ИД сущности, а по ИД банка
    default BankRateListDocument readByBankId(int id) throws EntityNotFoundException {
        CurrRateDocumentsRepoInMongoExtends mr = (CurrRateDocumentsRepoInMongoExtends) getRep();
        BankRateListDocument cd = mr.findByBankId(id);
        if (cd == null) {
            throw new EntityNotFoundException(getEntity().getClass() + " with ID " + id + " not found");
        }
        return cd;
    }

    default void update(T obj) {
        //update = save
        getRep().save(obj);
    }

    default void delete(int key) {
        getRep().deleteById(key);
    }
}
