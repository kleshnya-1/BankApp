package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUnstatic;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

@Log4j2
@Repository
public class AccountDAOImpl implements IMaintainableDAO<Account> {

    @Override
    public Account read(int key) {
        //todo close session
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Account.class, key);
    }
}
