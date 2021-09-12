package ru.laptseu.bankapp.dao;

import lombok.Getter;
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
public class AccountDAOImpl implements IMaintainableDAO<Account> {

    SessionFactory sessionFactory;
    @Autowired
    public AccountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public AccountDAOImpl(){

    }

// TODO:  fix to default method in Interface
    @Override
    public Account read(int key) {
        Account b;
        //todo close session
        try (Session session = sessionFactory.openSession()) {
            b = session.get(Account.class, key);
            session.close();
            //todo ref with stream
            if (b != null) {
                return b;
            } else throw new EntityNotFoundException("Account not found with ID: " + key);
        }
    }
}
