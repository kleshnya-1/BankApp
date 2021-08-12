package ru.laptseu.bankApp.services;

import ru.laptseu.bankApp.dao.BankDaoImpl;
import ru.laptseu.bankApp.models.Bank;

import java.util.Scanner;

public class BankService implements IMaintainableService {
    BankDaoImpl bankDaoImpl = new BankDaoImpl();
    Scanner bankServiceScanner = new Scanner(System.in);

    @Override
    public boolean create() {

        Bank bank = new Bank();

        System.out.println("Введите информацию о банке в порядке: " +
                "имя, % комиссии физ и юр лиц, курс USD, курс EUR\n" +
                "первый банк, 6, 12, 2.63, 3.06");

        String bankRaw = bankServiceScanner.nextLine();
        String[] bankS = bankRaw.split(",");

        bank.setName(bankS[0]);
        bank.setTransferFeeInPercent(Double.parseDouble(bankS[1]));
        bank.setTransferFeeInPercentForNotNaturalPersons(Double.parseDouble(bankS[2]));
        bank.setUSDrate(Double.parseDouble(bankS[3]));
        bank.setEURrate(Double.parseDouble(bankS[4]));
        bankDaoImpl.create(bank);

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
}
