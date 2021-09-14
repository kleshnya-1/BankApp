package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

public interface BankRepo extends CrudRepository<Bank, Integer> {
    default void update(Bank c){
        save(c);
    }
    Bank readById(int id);


    default Bank read(int id){
        return  readById(id);
    }
}
