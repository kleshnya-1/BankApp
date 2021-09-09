package ru.laptseu.bankapp.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.TransferHistory;


@Component
public class HibernateSessionFactoryUnstatic {
    @Autowired
    public   SessionFactory sessionFactory;
    public HibernateSessionFactoryUnstatic() {    }

    public  SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                // configuration.configure("/hibernate.cfg.xml");
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Bank.class);
                configuration.addAnnotatedClass(Client.class);
                // configuration.addAnnotatedClass(CurrencyRate.class);
                //todo configuration.addAnnotatedClass(Currency.class);
                configuration.addAnnotatedClass(TransferHistory.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
