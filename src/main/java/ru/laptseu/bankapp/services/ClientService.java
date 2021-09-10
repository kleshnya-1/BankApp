package ru.laptseu.bankapp.services;

import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;

//todo in progress
@Service
public class ClientService implements IMaintainableService<Client> {

    IMaintainableDAO<Client> clientDao = DaoFactory.get(Client.class);

    @Override
    public int persist(Client o) throws SQLException {
        int id = clientDao.save(o);
        return id;
    }

    @Override
    public Client create(String[] paramArrClient) {
        Client client = new Client();
        client.setName(paramArrClient[0]);
        client.setNaturalPerson(Boolean.parseBoolean(paramArrClient[1]));
        return client;
    }

    @Override
    public Client read(int key) throws SQLException {
        return clientDao.read(key);
    }

    @Override
    public Client read(Currency currency, int key) throws SQLException {
        throw  new  UnsupportedOperationException("Only for currencyRate");
    }

    @Override
    public void update(String[] paramArr) throws SQLException {
        Client client;
        client = create(paramArr);
        client.setId(Integer.parseInt(paramArr[paramArr.length]));
        clientDao.update(client);
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
