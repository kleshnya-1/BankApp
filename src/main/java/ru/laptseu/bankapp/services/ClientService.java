package ru.laptseu.bankapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.ClientRepo;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;

//todo in progress
@Service
public class ClientService implements IMaintainableService<Client> {

    //IMaintainableDAO<Client> clientDao = DaoFactory.get(Client.class);

    @Autowired
    ClientRepo clientDao;

    @Override
    public int save(Client o) throws SQLException {
        int id = clientDao.save(o).getId();
        return id;
    }

    @Override
    public Client read(int key) throws SQLException {
        return clientDao.read(key);
    }

    //@Override
    public Client read(Currency currency, int key) throws SQLException {
        throw new UnsupportedOperationException("Only for currencyRate");
    }

    @Override
    public void update(Client client) throws SQLException {
        clientDao.update(client);
    }

    @Override
    public void delete(int key) throws SQLException {
        clientDao.delete(key);
    }
}
