package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.dao.repos.ClientRepo;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.Currency;

import java.sql.SQLException;

//todo in progress
@Service
@RequiredArgsConstructor
public class ClientService implements IMaintainableService<Client> {


        private final ClientDAOImpl clientDao;

    @Override
    public int save(Client o) throws SQLException {
        int id = clientDao.save(o).getId();
        return id;
    }

    @Override
    public Client read(int key) throws Throwable {
        return clientDao.read(key);
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
