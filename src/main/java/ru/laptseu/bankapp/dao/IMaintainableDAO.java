package ru.laptseu.bankapp.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.CustomDocument;
import ru.laptseu.bankapp.models.EntityModel;
import ru.laptseu.bankapp.models.uperED;

public interface IMaintainableDAO<T extends uperED> {

    T getEntity();

    CrudRepository getRep();

    default T save(T obj) {
        return (T) getRep().save(obj);
    }

    default T read(int id) throws Throwable {
        return (T) getRep().findById(id).orElseThrow(()-> new EntityNotFoundException(getEntity().getClass()+" with ID "+ id +" not found"));
    }

    default CustomDocument readByBankId(int id)   {
        CurrRateDocumentsRepoInMongoExtends mr = (CurrRateDocumentsRepoInMongoExtends) getRep();
        CustomDocument cd = mr.findByBankId(id);
        if (cd == null) {
            throw new EntityNotFoundException(getEntity().getClass() + " with ID " + id + " not found");
        }
        return null;
    }

    //update = save
    default void update(T obj) {
        getRep().save(obj);
    }
    default void delete(int key) {
        getRep().deleteById(key);
    }
}
