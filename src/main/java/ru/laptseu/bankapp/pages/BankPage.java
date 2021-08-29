package ru.laptseu.bankapp.pages;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.services.IMaintainableService;
import ru.laptseu.bankapp.services.ServiceFactory;

import java.sql.SQLException;

@Log4j2
public class BankPage implements IPages {
    public void enter(int id) {
        IMaintainableService<Bank> bankService = ServiceFactory.get(Bank.class);
        Bank bank = null;
        try {
            bank = bankService.read(id);
        } catch (SQLException e) {
            log.error(e);
        }
        if (bank == null) {
            System.out.println("Ид такого банка " + id + " не существует. Повторите попытку ");
        } else {
            System.out.println("Вы вошли как банк с ID " + id);
            startPage();
        }
    }

    private void startPage() {
        System.out.println();
        System.out.println("***Функции в разработке***");
        System.out.println("1. Сменить название");
        System.out.println("2. Найти клиента по ИД");
    }

}
