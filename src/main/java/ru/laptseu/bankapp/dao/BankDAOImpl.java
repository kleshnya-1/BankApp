package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

import java.sql.SQLException;

@Log4j2
@Component
public class BankDAOImpl implements IMaintainableDAO<Bank> {
//todo ask i use DAO, not SERVICE?


    private CurrencyRateDAOImpl currencyRateDAO= new CurrencyRateDAOImpl();

    public BankDAOImpl() {
    }

    @Override
    public int save(Bank obj) throws SQLException {
        //todo ref and reuse it
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(obj);
            currencyRateDAO.save(obj.getCurrencyRates());
            tx1.commit();
        } finally {
            session.close();
            return obj.getId();
        }
    }

    @Override
    public Bank read(int key) {
        Bank b;
        //todo close session
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            b = session.get(Bank.class, key);
            //todo ref with stream
            if (b != null) {
                b.setCurrencyRates(currencyRateDAO.read(b.getId()));
                for (CurrencyRate cr : b.getCurrencyRates()) {
                    cr.setBank(b);
                    cr.setBankId(b.getId());
                }
            }
        }
        return b;
    }

    @Override
    public void update(Bank obj) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        update(obj, session);
        currencyRateDAO.save(obj.getCurrencyRates());
        session.getTransaction().commit();
        session.close();
    }
}
