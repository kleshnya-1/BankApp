package ru.laptseu.bankapp.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.laptseu.bankapp.models.CurrencyRate;

//todo не работает
public class HibernateSessionFactoryUtil2 {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil2() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("/mongo-pg.cfg.xml");

                configuration.addAnnotatedClass(CurrencyRate.class);
                //todo configuration.addAnnotatedClass(Currency.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}

//package ru.laptseu.bankapp.utilities;
//
//
//
////import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.SessionFactory;
//
//import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;
//import ru.laptseu.bankapp.models.CurrencyRate;
//
//public class HibernateSessionFactoryUtil2 {
//    private static SessionFactory sessionFactory;
//
//    private HibernateSessionFactoryUtil2() {
//    }
//
//    public static SessionFactory getSessionFactoryMongo() {
//        if (sessionFactory == null) {
//            try {
//                Configuration cfg1 = new AnnotationConfiguration();
//                cfg1.configure("/mongo-pg.cfg.xml");
//                cfg1.addAnnotatedClass(CurrencyRate.class); // mapped classes
//
//                SessionFactory sessionFactory = cfg1.buildSessionFactory();
//
//            } catch (Exception e) {
//                System.out.println("Исключение!" + e);
//            }
//        }
//        return sessionFactory;
//    }
//
//}
