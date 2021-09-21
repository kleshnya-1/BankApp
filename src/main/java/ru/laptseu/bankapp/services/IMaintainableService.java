package ru.laptseu.bankapp.services;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Model;

public interface IMaintainableService<T extends Model> {

    CrudRepository getDao();

    default T save(T obj) {
        return (T) getDao().save(obj);
    }

    default T read(int id) {
        return (T) getDao().findById(id).get();
    }

    default void update(T obj) {
        //update = save
        getDao().save(obj);
    }

    default void delete(int key) {
        getDao().delete(read(key));
    }
    default void delete(T key) {
        getDao().delete(key);
    }
}
