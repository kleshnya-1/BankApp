package ru.laptseu.bankapp.core;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.EntityNotFoundException;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.pages.AdminPage;
import ru.laptseu.bankapp.pages.IPages;
import ru.laptseu.bankapp.pages.PageFactory;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j2
public class Initial {

    AdminPage adminPage = new AdminPage();
    Scanner scanner = new Scanner(System.in);

    public void startApp() {
        boolean isRunning = true;
        //todo ask. я бы как-то вложил сюда возможность при вводе "Команды" возле реализации выхода в дальнейшем.
        System.out.println("\nПриложение запущенно. \nДля обзора команд в дальнейшем введите \"Команды\"");

        while (isRunning) {
            try {
                IPages pageByRoleInSystem = roleAskingMethod();
                if (pageByRoleInSystem != null) {
                    pageByRoleInSystem.enter(checkId(pageByRoleInSystem));
                    isRunning = false;
                }
                if (isRunning == false) {
                    System.out.println("Закончить выполнение програмы? Y/N");
                    isRunning = (scanner.nextLine().equalsIgnoreCase("Y"));
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

    public IPages roleAskingMethod() throws SQLException {
        IPages page = null;
        System.out.println("Войти как: (Использовать цифры)");
        System.out.println("1. Клиент");
        System.out.println("2. Банк");
        System.out.println("3. Администратор");

        int roleNum = scanner.nextInt();
        //todo exception in scanner

        switch (roleNum) {
            case 1:
                page = PageFactory.get(Client.class);
                break;
            case 2:
                page = PageFactory.get(Bank.class);
                break;
            case 3:
                page = adminPage;
                break;
        }
        return page;
    }

    //todo. ask. method or different class? i mean S from SOLID?
    public int checkId(IPages page) {
        //admin dont need an id.
        if (page.equals(adminPage)) return 0;
        System.out.println("Чтоб войти как " + page.getClass() + " введите ID");
        return scanner.nextInt();
    }
}




