package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Client;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer>  {

    default void update(Account c){
        save(c);
    }
    Account readById(int id);


    default Account read(int id){
        return  readById(id);
    }
}
