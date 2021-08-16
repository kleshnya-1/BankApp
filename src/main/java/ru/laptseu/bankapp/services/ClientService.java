package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.ClientDaoImpl;
import ru.laptseu.bankapp.models.Client;

import java.util.Scanner;

public class ClientService implements IMaintainableService {
    private Scanner clientServiceScanner = new Scanner(System.in);
    private ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

    @Override
    public boolean create() {
        Client client = new Client();
        System.out.println("Введите информацию о клиенте в порядке: " +
                "имя, Физ лицо(true/false)\n" +
                "Иван Петров, true");

        String clientRaw = clientServiceScanner.nextLine();
        String[] clients = clientRaw.split(", ");
        client.setName(clients[0]);
        System.out.println(clients[1]);
        client.setNaturalPerson(Boolean.parseBoolean(clients[1]));
        System.out.println(client.isNaturalPerson());
        clientDaoImpl.create(client);
        return true;
    }

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    public void showAccountsByName(String name) {

        clientDaoImpl.showAccountsByName(name);

    }
}
