package ru.laptseu.bankapp.core;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.dao.AccountDAOImpl;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.pages.AdminPage;
import ru.laptseu.bankapp.pages.IPages;
import ru.laptseu.bankapp.pages.PageFactory;
import ru.laptseu.bankapp.services.AccountService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class Initial {
    IPages page;
    AdminPage adminPage = new AdminPage();
    Scanner scanner = new Scanner(System.in);

    public void startApp() {
        boolean isRunning = true;
        System.out.println("\nПриложение запущенно. \nДля обзора команд в дальнейшем введите \"Команды\"");

        while (isRunning) {
            try {
                roleAskingMethod();
                isRunning = false;
//                input = scanner.nextLine();
//                String[] commands = input.split("\\s");
//                isRunning = true;

//                switch (commands[0]) {
//                    case "Команды":
//                        demo();
//                        break;
//                    case "Добавить":
//                        switch (commands[1]) {
//                            //todo in progress
//                            /*case "банк":
//                                //todo  bankService.getCurrencyList
//                                System.out.println("Введите информацию о банке в порядке: " +
//                                        "имя, % комиссии физ и юр лиц, курс USD, курс EUR\n" +
//                                        "первый банк, 6, 12, 2.63, 3.06");
//                                if (bankService.create())
//                                    System.out.println("Счет на имя " + account.getClientName() + " добавлен");
//                                startApp();
//                                break;
//                            case "клиент":
//                                System.out.println("Введите информацию о клиенте в порядке: " +
//                                        "имя, Физ лицо(true/false)\n" +
//                                        "Иван Петров, true");
//                                clientService.create();
//                                startApp();
//                                break;*/
//                            case "счет":
//                                System.out.println("Введите информацию о счете в порядке: " +
//                                        "имя владельца, ид банка, валюта(BYN, USD, EUR), сумма\n" +
//                                        "Иван, первый банк, USD, 150");
//                                System.out.println();
//                                //todo move scanner up or refactor with scanner method
//
//                                String accountRaw = scanner.nextLine();
//                                String[] accountParametersArray = accountRaw.split(",");
//                                int acId;
//                                try {
//                                    Account account = accountService.create(accountParametersArray);
//                                    acId = accountService.persist(account);
//                                } catch (SQLException e) {
//                                    log.error(e);
//                                    throw e;
//                                }
//                                System.out.println("Account created with id " + acId);
//                                break;
//                            default:
//                                System.out.print("Невозможно понять сущность ");
//                                Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
//                                System.out.println();
//                                break;
//                        }
//                        break;
//                    //todo in progress
//                    /*case "Удалить":
//                        switch (commands[1]) {
//                            case "банк":
//                                bankService.delete();
//                                break;
//                            case "клиент":
//                                clientService.delete();
//                                break;
//                            case "счет":
//                                accountService.delete();
//                                break;
//                            default:
//                                System.out.print("Невозможно понять сущность ");
//                                Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
//                                System.out.println();
//
//                                break;
//                        }
//                        break;
//                    case "Обновить":
//                        switch (commands[1]) {
//                            case "банк":
//                                bankService.update();
//                                break;
//                            case "клиент":
//                                clientService.update();
//                                break;
//                            case "счет":
//                                accountService.update();
//                                break;
//                            default:
//                                System.out.print("Невозможно понять сущность ");
//                                Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
//                                System.out.println();
//
//                                break;
//                        }
//                        break;
//                    case "Перевести":
//                        Account fromA = accountDaoImpl.readByName(commands[1], commands[2]);
//                        Account toA = accountDaoImpl.readByName(commands[3], commands[4]);
//                        double amount = Double.parseDouble((commands[5]));
//                        accountService.transferAmount(fromA, toA, amount);
//                        break;
//                    case "Вывести":
//                        clientService.showAccountsByName(commands[1]);
//                        break;
//                    case "Транзакции":
//                        transactionService.showTransactionByNameAndDate(commands[1], Integer.parseInt(commands[2]));
//                        break;
//                    default:
//                        System.out.print("Невозможно понять команду ");
//                        Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
//                        System.out.println();
//
//                        break;*/
//
//                }
                if (isRunning == false) {
                    System.out.println("Закончить выполнение програмы? Y/N");
                    isRunning = (scanner.nextLine().equalsIgnoreCase("Y") ? true : false);
                }

            } catch (InputMismatchException fg) {
                log.error(fg);
                System.out.print("Вы ввели не то. ");
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error(e);
                System.out.println("Ошибка ввода (не хватает аргументов)");
            } catch (EntityNotFoundException e) {
                log.error(e);
                System.out.println("Нет такой сущности");
            } catch (SQLException e) {
                log.error(e);
                //todo text in exc
                System.out.println("???Ошибка ввода (не хватает аргументов)");
            }
        }

    }

    public void roleAskingMethod() throws SQLException {
        System.out.println("Войти как: (Использовать цифры)");
        System.out.println("1. Клиент");
        System.out.println("2. Банк");
        System.out.println("3. Администратор");

        int roleNum = scanner.nextInt();
        //todo exception in scanner

        switch (roleNum) {
            case 1:
                page = PageFactory.get(Client.class);
                page.enter(checkId("Клиент"));
                break;
            case 2:
                page = PageFactory.get(Bank.class);
                page.enter(checkId("Банк"));
                break;
            case 3:
                page = PageFactory.get(Client.class);
                adminPage.enter();
                break;
        }
    }

    //todo or different class? is to S from SOLID?
    public int checkId(String who) {
        System.out.println("Чтоб войти как " + who + " введите ID");
        return scanner.nextInt();
    }

    public void demo() {
        System.out.println("\nОбращениее к приложению выполняется в формате КОМАНДА СУЩНОСТЬ");
        System.out.print("Доступные команды: Добавить, Удалить, Обновить.");
        System.out.println(" Доступные сущности для них: банк, клиент, счет. \n");
        System.out.println("Команда всегда начинаентся с большой буквы, сущность/значение с маленькой");
        System.out.println("Далее приводятся инструкции для дальнейших действий \n");

        System.out.println("Иные команды имеют формат КОМАНДА ЗНАЧЕНИЯ, например:");
        System.out.println("Перевести ИМЯ_КЛИЕНТА1, БАНК1, ИМЯ_КЛИЕНТА2, БАНК2, СУММА_В_ВАЛЮТЕ_ОТПРАВИТЕЛЯ");
        System.out.println("Вывести ИМЯ_КЛИЕНТА - для выведения всех счетов");
        System.out.println("Транзакции ЧИСЛО_ДНЕЙ ИМЯ_КЛИЕНТА - для выведения всех транзакций за окзанные дни до сегодня\n");
    }
}




