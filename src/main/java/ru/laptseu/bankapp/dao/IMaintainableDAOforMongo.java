package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.CustomDocument;

public interface IMaintainableDAOforMongo<T extends CustomDocument> {

    T getEntity();

    CurrRateDocumentsRepoInMongoExtends getRep();

    default T save(T obj) {
        return (T) getRep().save(obj);
    }

    default T read(int id) throws Throwable {
        T ret = (T) getRep().findByBankId(id);

        if (ret!=null) {
            return ret;
        } else  throw new EntityNotFoundException(getEntity().getClass()+" with ID "+ id +" not found");
    }
    //update = save
    default void update(T obj) {
        getRep().save(obj);
    }
    default void delete(int key) {
        getRep().deleteByBankId(key);
    }
}
