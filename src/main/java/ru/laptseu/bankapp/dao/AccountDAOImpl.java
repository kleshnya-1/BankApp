package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.models.Account;

@Log4j2
@Getter
@Repository
@RequiredArgsConstructor
public class AccountDAOImpl implements IMaintainableDAO<Account> {

    @Autowired AccountRepo rep;
    Account entity = new Account();

// TODO:  fix to default method in Interface
    @Override
    public Account read(int key) {



    }
    //else throw new EntityNotFoundException("Account not found with ID: " + key);
}
