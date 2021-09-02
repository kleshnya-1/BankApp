package ru.laptseu.bankapp.core;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.laptseu.bankapp.models.*;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Bank.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(CurrencyRate.class);
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
