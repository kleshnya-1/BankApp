package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.models.Client;

@Log4j2
@Getter
@Repository
public class ClientDAOImpl implements IMaintainableDAO<Client> {

    SessionFactory sessionFactory;

    @Autowired
    public ClientDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client read(int key) {
        Client b;
        //todo close session
        try (Session session = sessionFactory.openSession()) {
            b = session.get(Client.class, key);
            //todo ref with stream
            if (b != null) {
                return b;
            } else throw new EntityNotFoundException("Client not found with ID: " + key);
        }
    }

}
