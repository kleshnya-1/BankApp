package ru.laptseu.bankapp.core;

import ru.laptseu.bankapp.dao.AccountDaoImpl;
import ru.laptseu.bankapp.dao.ClientDaoImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.services.AccountService;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.ClientService;
import ru.laptseu.bankapp.services.TransactionService;
import ru.laptseu.bankapp.utilities.Commands;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Initial {
    public static void startApp() {
        BankService bankService = new BankService();
        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();
        AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
        ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
        TransactionService transactionService = new TransactionService();
        Commands.demo();
        System.out.println("\nПриложение запущенно. \nДля обзора команд в дальнейшем введите \"Команды\"");

        String input;
        String[] commands;
        while (true) {
            Scanner sc1 = new Scanner(System.in);
            try {
                input = sc1.nextLine();
                commands = input.split("\\s");
                break;
            } catch (InputMismatchException fg) {

                System.out.print("Вы ввели не то. ");
            }
        }

        try {
            switch (commands[0]) {
                case "Команды":
                    Commands.demo();
                    startApp();
                    break;

                case "Добавить":
                    switch (commands[1]) {
                        case "банк":
                            bankService.create();
                            startApp();
                            break;
                        case "клиент":
                            clientService.create();
                            startApp();
                            break;
                        case "счет":
                            accountService.create();
                            startApp();
                            break;
                        default:
                            System.out.print("Невозможно понять сущность ");
                            Arrays.stream(commands).map(s -> s + " ").forEach(System.out::print);
                            System.out.println();
                            startApp();
                            break;
                    }
                    break;
                case "Удалить":
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
                            startApp();
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
                            startApp();
                            break;
                    }
                    break;
                case "Перевести":
                    Account fromA = (Account) accountDaoImpl.readByName(commands[1], commands[2]);
                    Account toA = (Account) accountDaoImpl.readByName(commands[3], commands[4]);
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
                    startApp();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка ввода (не хватает аргументов)");
            startApp();
        }
    }
}

