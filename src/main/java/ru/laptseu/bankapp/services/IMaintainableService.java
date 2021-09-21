package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Model;
import ru.laptseu.bankapp.models.ModelWithIntegerId;

public interface IMaintainableService<T extends Model> {

    IMaintainableDAO getDao();

    default T save(T obj) {
        return (T) getDao().save(obj);
    }

    default T read(int id) throws Throwable {
        return (T) getDao().read(id);
    }

    default void update(T obj) {
        //update = save
        getDao().save(obj);
    }

    default void delete(int key) {
        getDao().delete(key);
    }
}
