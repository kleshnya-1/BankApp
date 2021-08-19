package ru.laptseu.bankapp.core;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.dao.AccountDAOImpl;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.services.AccountService;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.ClientService;
import ru.laptseu.bankapp.services.TransactionService;
import ru.laptseu.bankapp.utilities.Commands;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class Initial {

    public static void startApp() {
        BankService bankService = new BankService();
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();
        AccountDAOImpl accountDaoImpl = new AccountDAOImpl();
        ClientDAOImpl clientDaoImpl = new ClientDAOImpl();
        TransactionService transactionService = new TransactionService();
        boolean isFinished;
        Commands.demo();
        System.out.println("\nПриложение запущенно. \nДля обзора команд в дальнейшем введите \"Команды\"");
        String input;
        //is it correct to put 100?
        String[] commands = new String[100];
        while (isFinished = false) {
            Scanner sc1 = new Scanner(System.in);
            try {
                input = sc1.nextLine();
                commands = input.split("\\s");
                break;
            } catch (InputMismatchException fg) {
                System.out.print("Вы ввели не то. ");
            }
            try {
                switch (commands[0]) {
                    case "Команды":
                        Commands.demo();
                        break;
                    case "Добавить":
                        switch (commands[1]) {
                            //todo in progress
                            /*case "банк":
                                //todo  bankService.getCurrencyList
                                System.out.println("Введите информацию о банке в порядке: " +
                                        "имя, % комиссии физ и юр лиц, курс USD, курс EUR\n" +
                                        "первый банк, 6, 12, 2.63, 3.06");
                                if (bankService.create())
                                    System.out.println("Счет на имя " + account.getClientName() + " добавлен");
                                startApp();
                                break;
                            case "клиент":
                                System.out.println("Введите информацию о клиенте в порядке: " +
                                        "имя, Физ лицо(true/false)\n" +
                                        "Иван Петров, true");
                                clientService.create();
                                startApp();
                                break;*/
                            case "счет":
                                System.out.println("Введите информацию о счете в порядке: " +
                                        "имя владельца, ид банка, валюта(BYN, USD, EUR), сумма\n" +
                                        "Иван, первый банк, USD, 150");
                                System.out.println();
                                //todo move scanner up or refactor with scanner method
                                Scanner accountServiceScanner = new Scanner(System.in);
                                String accountRaw = accountServiceScanner.nextLine();
                                String[] accountParametersArray = accountRaw.split(",");
                                int acId;
                                try {
                                    Account account = accountService.create(accountParametersArray);
                                    acId = accountService.persist(account);
                                } catch (SQLException throwables) {
                                    log.error(throwables);
                                    throw throwables;
                                }
                                System.out.println("Account created with id " + acId);
                                break;
                            default:
                                System.out.print("Невозможно понять сущность ");
                                Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
                                System.out.println();
                                break;
                        }
                        break;
                    //todo in progress
                    /*case "Удалить":
                        switch (commands[1]) {
                            case "банк":
                                bankService.delete();
                                break;
                            case "клиент":
                                clientService.delete();
                                break;
                            case "счет":
                                accountService.delete();
                                break;
                            default:
                                System.out.print("Невозможно понять сущность ");
                                Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
                                System.out.println();

                                break;
                        }
                        break;
                    case "Обновить":
                        switch (commands[1]) {
                            case "банк":
                                bankService.update();
                                break;
                            case "клиент":
                                clientService.update();
                                break;
                            case "счет":
                                accountService.update();
                                break;
                            default:
                                System.out.print("Невозможно понять сущность ");
                                Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
                                System.out.println();

                                break;
                        }
                        break;
                    case "Перевести":
                        Account fromA = accountDaoImpl.readByName(commands[1], commands[2]);
                        Account toA = accountDaoImpl.readByName(commands[3], commands[4]);
                        double amount = Double.parseDouble((commands[5]));
                        accountService.transferAmount(fromA, toA, amount);
                        break;
                    case "Вывести":
                        clientService.showAccountsByName(commands[1]);
                        break;
                    case "Транзакции":
                        transactionService.showTransactionByNameAndDate(commands[1], Integer.parseInt(commands[2]));
                        break;
                    default:
                        System.out.print("Невозможно понять команду ");
                        Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
                        System.out.println();

                        break;*/
                }
            } catch (ArrayIndexOutOfBoundsException | SQLException e) {
                System.out.println("Ошибка ввода (не хватает аргументов)");
            }
            if (isFinished = true) {
                System.out.println(("Работа програмы окончена"));
                break;
            }
        }
    }
}




