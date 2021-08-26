package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.DaoFactory;
import ru.laptseu.bankapp.dao.IMaintainableDAO;
import ru.laptseu.bankapp.models.Client;

import java.sql.SQLException;

//todo in progress
public class ClientService implements IMaintainableService<Client> {

    IMaintainableDAO<Client> clientDao = DaoFactory.get(Client.class);

    @Override
    public int persist(Client o) throws SQLException {
        int id = clientDao.create(o);
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
    public void update(String[] paramArr) throws SQLException {
        Client client;
        client = create(paramArr);
        client.setId(Integer.parseInt(paramArr[paramArr.length]));
        clientDao.update(client);
    }

    @Override
    public void delete(int key) throws SQLException {
        clientDao.delete(key);
    }
}
