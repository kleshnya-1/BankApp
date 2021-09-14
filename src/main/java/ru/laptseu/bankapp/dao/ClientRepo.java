package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

public interface ClientRepo extends CrudRepository<Client, Integer> {


    default void update(Client c){
        save(c);
    }
    Client readById(int id);


    default Client read(int id){
        return  readById(id);
    }

    void deleteById(int id);

    default void delete(int id){
        deleteById(id);
    }
}
