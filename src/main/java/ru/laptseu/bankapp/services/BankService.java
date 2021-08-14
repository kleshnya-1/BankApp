package ru.laptseu.bankapp.services;

import ru.laptseu.bankapp.dao.BankDaoImpl;
import ru.laptseu.bankapp.models.Bank;

import java.util.Scanner;

public class BankService implements IMaintainableService {
    private BankDaoImpl bankDaoImpl = new BankDaoImpl();
    private Scanner bankServiceScanner = new Scanner(System.in);

    @Override
    public boolean create() {
         Bank bank = new Bank();

        System.out.println("Введите информацию о банке в порядке: " +
                "имя, % комиссии физ и юр лиц, курс USD, курс EUR\n" +
                "первый банк, 6, 12, 2.63, 3.06");

        String bankRaw = bankServiceScanner.nextLine();
        String[] banks = bankRaw.split(",");

        bank.setName(banks[0]);
        bank.setTransferFeeInPercent(Double.parseDouble(banks[1]));
        bank.setTransferFeeInPercentForNotNaturalPersons(Double.parseDouble(banks[2]));
        bank.setUSDrate(Double.parseDouble(banks[3]));
        bank.setEURrate(Double.parseDouble(banks[4]));
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
