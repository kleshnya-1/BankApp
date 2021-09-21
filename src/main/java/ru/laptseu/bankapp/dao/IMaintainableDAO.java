package ru.laptseu.bankapp.dao;

import ru.laptseu.bankapp.models.Model;

public interface IMaintainableDAO<T extends Model> {
//    T getEntity();
//
//    CrudRepository getRep();
//
//    default T save(T obj) {
//        return (T) getRep().save(obj);
//    }
//
//    default T read(int id) throws Throwable {
//
//            return (T) getRep().findById(id).orElseThrow(() -> new EntityNotFoundException(getEntity().getClass() + " with ID " + id + " not found"));
//
//    }
//
//    default void update(T obj) {
//        //update = save
//        getRep().save(obj);
//    }
//
//    default void delete(int key) {
//        getRep().deleteById(key);
//    }
}
