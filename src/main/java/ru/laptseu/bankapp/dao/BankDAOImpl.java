package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

import java.sql.SQLException;

@Log4j2
@Repository
public class BankDAOImpl implements IMaintainableDAO<Bank> {
    //todo ask i use for currency access DAO, not SERVICE?

    @Autowired
    //todo сюда в тестах не подтягивается бин. хоть его и видно, если на зерно нажать
    CurrencyRateDAOImpl currencyRateDAO ;//= new CurrencyRateDAOImpl();

    public BankDAOImpl() {
    }


//    public BankDAOImpl(CurrencyRateDAOImpl currencyRateDAO) {
//        this.currencyRateDAO = currencyRateDAO;
//    }

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
        //todo if-for-if но у меня иначе нет идей. хайбернейт сам их не связывает. банк для экономии
        // места обнуляется при сохранении в бд (можно бы и переопрделить объект курса и в нем убрать
        // ид банка. ид банка нужен при операциях с курсом как с объектом отдельно, но не нужен уже после
        // того, как его сохранили.
//        Bank j1 = obj;
//        if (!obj.getCurrencyRates().isEmpty())  {
//            for (CurrencyRate cr: obj.getCurrencyRates()){
//                if (cr.getBankId()==0) cr.setBankId(obj.getId());
//                CurrencyRate aaa = cr;
//                System.out.println(currencyRateDAO.toString());
//                currencyRateDAO.save(cr);
//            }
//
//        }
        session.getTransaction().commit();
        session.close();
    }
}
