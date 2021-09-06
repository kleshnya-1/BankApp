package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

@Log4j2
@Component
public class ClientDAOImpl implements IMaintainableDAO<Client> {
    @Override
    public Client read(int key) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, key);
    }

}
