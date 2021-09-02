package ru.laptseu.bankapp.dao;

import com.mongodb.BasicDBObject;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

@Log4j2
public class BankDAOImpl implements IMaintainableDAO<Bank> {
    //todo mongo in currencyRates

    @Override
    public Bank read(int key) {
        Bank b;
        //todo close session
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            b = session.get(Bank.class, key);
        }
        return b;
    }
}
