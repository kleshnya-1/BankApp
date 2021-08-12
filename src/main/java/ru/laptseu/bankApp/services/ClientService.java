package ru.laptseu.bankApp.services;

import ru.laptseu.bankApp.dao.ClientDaoImpl;
import ru.laptseu.bankApp.models.Client;

import java.util.Scanner;

public class ClientService implements IMaintainableService {
    Scanner clientServiceScanner = new Scanner(System.in);
    ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

    @Override
    public boolean create() {
        Client client = new Client();


        System.out.println("Введите информацию о клиенте в порядке: " +
                "имя, Физ лицо(true/false)\n" +
                "Иван Петров, true");

        String clientRaw = clientServiceScanner.nextLine();
        String[] clientsS = clientRaw.split(", ");

        client.setName(clientsS[0]);

        System.out.println(clientsS[1]);
        client.setNaturalPerson(Boolean.parseBoolean(clientsS[1]));


        System.out.println(client.isNaturalPerson());
        clientDaoImpl.create(client);

        return false;
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
