package ru.laptseu.bankapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.EntityModel;

import java.sql.SQLException;

public interface IMaintainableDAO<T extends EntityModel> {

   //todo ref

     Object getRepos();

    //todo DRY it
     int save(T obj) ;

     void update(T obj) ;

     void delete(int key);

     void delete(T key);

 T readById(int id);

    default T read(int id){
        return  readById(id);
    }
}
