package ru.laptseu.bankapp.pages;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.services.IMaintainableService;
import ru.laptseu.bankapp.services.ServiceFactory;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

// TODO: 10.09.2021 for removing
@Log4j2
public class AdminPage implements IPages {

    Scanner scanner = new Scanner(System.in);

    public void enter() throws SQLException {
        try {
            System.out.println("Вошли как Админ");
            System.out.println("Что необходимо сделать");
            System.out.println("1. Добавить");
            System.out.println("2. Прочесть");
            System.out.println("3. Обновить");
            System.out.println("4. Удалить");
            System.out.println("5. Выйти");

            //todo validator
            int activity = scanner.nextInt();

            System.out.println("1. Аккаунт");
            System.out.println("2. Банк");
            System.out.println("3. Клиента");
            System.out.println("4. Курс");
            //todo exit
            System.out.println("5. Выйти");

            //todo validator
            int entity = scanner.nextInt();

            Object entityObject = new Object();

            switch (entity) {
                case 1:
                    entityObject = new Account();
                    System.out.println("Создать аккауент(счет): Название банка, ИД клиента, валюта, остаток");
                    System.out.println("Например: Новый, 2, USD, 100");
                    System.out.println("Удалить и прочесть: просто введите ИД");
                    System.out.println("Обновить: Аналогично созданию, но после последнего аргумента добавьте ИД");
                    break;
                case 2:
                    entityObject = new Bank();
                    System.out.println("Создать банк: название, комиссия для ЮР и физ лиц в %");
                    System.out.println("Например: Нулевой банк, 10, 20");
                    System.out.println("Удалить и прочесть: просто введите ИД");
                    System.out.println("Обновить: Аналогично созданию, но после последнего аргумента добавьте ИД");
                    break;
                case 3:
                    entityObject = new Client();
                    System.out.println("Создать клиента: имя, физ лицо ли он?");
                    System.out.println("Например: Иван Петров, true");
                    System.out.println("Удалить и прочесть: просто введите ИД");
                    System.out.println("Обновить: Аналогично созданию, но после последнего аргумента добавьте ИД");
                    break;
                case 4:
                    entityObject = new CurrencyRate();
                    System.out.println("Создать(добавить) курс валют: Название банка, валюта, курс к BYN");
                    System.out.println("Например: Новый, USD, 2.65");
                    System.out.println("Удалить и прочесть: просто введите ИД");
                    System.out.println("Обновить: Аналогично созданию, но после последнего аргумента добавьте ИД");
                    break;
                case 5:
                    //todo хочу тут выкинуть ошибку, которая бы вышла из всего до Inti-а
                    break;
            }

//        int rawAr2gumentsForCreating = scanner.nextInt();
            Scanner scanner2 = new Scanner(System.in);
//        String rawAr2gumentsForCreating2 = scanner.nextLine();
//        String rawAr2gumentsForCreating22 = scanner2.nextLine();
            // scanner.close();
            //  scanner.reset();
            String rawArgumentsForCreating = scanner2.nextLine();
            String[] argumentsForCreating = rawArgumentsForCreating.split(",");
            Arrays.stream(argumentsForCreating).forEach(s -> s.trim());
//todo check for object.class
// check for extending
            IMaintainableService dao = ServiceFactory.get(entityObject.getClass());

            switch (activity) {
                case 1:
                    int id = dao.persist(dao.create(argumentsForCreating));
                    System.out.println("Создание прошло успешно. присвоенный ID " + id);
                    break;
                case 2:
                    System.out.println(dao.read(Integer.parseInt(argumentsForCreating[0])));
                    break;
                case 3:
                    dao.update(argumentsForCreating);
                    System.out.println("Создание прошло успешно. присвоенный ID " + argumentsForCreating[argumentsForCreating.length]);
                    break;
                case 4:
                    dao.delete(Integer.parseInt(argumentsForCreating[0]));
                    System.out.println("Удален ID " + argumentsForCreating[0]);
                    break;
                //todo exit
            }

        } catch (NumberFormatException nfe) {
            log.error(nfe);
            System.out.println("Ошибка ввода. значение не может быть " + nfe.getMessage());
        }
    }

    @Override
    public void enter(int id) throws SQLException {
        enter();
    }
}
