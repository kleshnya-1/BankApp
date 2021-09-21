package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.models.Model;

public interface IMaintainableDAO<T extends Model> {
    T getEntity();

    CrudRepository getRep();

    default T save(T obj) {
        return (T) getRep().save(obj);
    }

    default T read(int id) throws Throwable {
        try {
            return (T) getRep().findById(id).orElseThrow(() -> new EntityNotFoundException(getEntity().getClass() + " with ID " + id + " not found"));
        } catch (Exception e){
            // TODO: 20.09.2021 exception type
            CurrRateDocumentsRepoInMongoExtends mr = (CurrRateDocumentsRepoInMongoExtends) getRep();
            BankRateListDocument cd = mr.findByBankId(id);
            return (T) cd;
        }
    }

    default void update(T obj) {
        //update = save
        getRep().save(obj);
    }

    default void delete(int key) {
        getRep().deleteById(key);
    }
}
