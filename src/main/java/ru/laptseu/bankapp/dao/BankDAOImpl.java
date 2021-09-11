package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.sql.SQLException;

@Log4j2
@Getter
@Setter
@Repository
public class BankDAOImpl implements IMaintainableDAO<Bank> {

    CurrencyRateDAOImpl currencyRateDAO;

    @Autowired
    SessionFactory sessionFactory;

    public BankDAOImpl() {
    }

    @Autowired
    public void setCurrencyRateDAO(CurrencyRateDAOImpl currencyRateDAO) {
        this.currencyRateDAO = currencyRateDAO;
    }

    @Override
    public int save(Bank obj) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.save(obj);
            currencyRateDAO.save(obj.getCurrencyRates());
        }
        return obj.getId();
    }

    @Override
    public Bank read(int key) {
        Bank b;
        //todo close session
        try (Session session = sessionFactory.openSession()) {
            b = session.get(Bank.class, key);
            //todo ref with stream
            if (b != null) {
                b.setCurrencyRates(currencyRateDAO.read(b.getId()));
                for (CurrencyRate cr : b.getCurrencyRates()) {
                    cr.setBank(b);
                    cr.setBankId(b.getId());
                }
                return b;
            } else throw new EntityNotFoundException("Bank not found with ID: " + key);
        }
    }

    @Override
    public void update(Bank obj) throws SQLException {
        Session session = sessionFactory.openSession();
        update(obj, session);
        session.getTransaction().commit();
        session.close();
    }
}
