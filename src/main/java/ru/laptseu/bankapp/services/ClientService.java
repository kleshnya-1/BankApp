package ru.laptseu.bankapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.models.Client;

//todo in progress
@Service
@RequiredArgsConstructor
public class ClientService implements IMaintainableService<Client> {


        private final ClientDAOImpl clientDao;

    @Override
    public int save(Client o) {
        int id = clientDao.save(o).getId();
        return id;
    }

    @Override
    public Client read(int key) throws Throwable {
        return clientDao.read(key);
    }


    @Override
    public void update(Client client) {
        clientDao.update(client);
    }

    @Override
    public void delete(int key) {
        clientDao.delete(key);
    }
}
