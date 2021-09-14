package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.exceptions.EntityNotFoundException;
import ru.laptseu.bankapp.models.EntityModel;

public interface IMaintainableDAO<T extends EntityModel> {

    T getEntity();

    CrudRepository getRep();

    default T save(T obj) {
        return (T) getRep().save(obj);
    }

    default T read(int id) throws Throwable {
        return (T) getRep().findById(id).orElseThrow(()-> new EntityNotFoundException(getEntity().getClass()+" with ID "+ id +" not found"));
    }
    //update = save
    default void update(T obj) {
        getRep().save(obj);
    }
    default void delete(int key) {
        getRep().deleteById(key);
    }
}
