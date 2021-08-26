package ru.laptseu.bankapp.pages;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.services.IMaintainableService;
import ru.laptseu.bankapp.services.ServiceFactory;

import java.sql.SQLException;

@Log4j2
public class ClientPage implements IPages {

    public void enter(int id){
        IMaintainableService<Client> clientService = ServiceFactory.get(Client.class);
        Client c = null;
        try {
            c = clientService.read(id);
        } catch (SQLException e) {
            log.error(e);
        }
        if (c==null){
            System.out.println("Ид такого клинта "+id+" не существует. Повторите попытку ");
        } else {
            System.out.println("Вы вошли как клиент с ID " +id);
            startPage();
        }
    }
    private  void startPage(){
        System.out.println();
        System.out.println("***Функции в разработке***");
        System.out.println("1. Провести перевод на счет по ИД");
        System.out.println("2. Состояние моих счетов");
        System.out.println("3. Сменить имя");
        System.out.println("4. Удалить аккаунт");
    }
}
