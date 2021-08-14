package ru.laptseu.bankapp.services;


import ru.laptseu.bankapp.dao.AccountDaoImpl;
import ru.laptseu.bankapp.dao.BankDaoImpl;
import ru.laptseu.bankapp.dao.ClientDaoImpl;
import ru.laptseu.bankapp.dao.TransferHistoryDAO;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.CommissionCalculator;
import ru.laptseu.bankapp.utilities.CurrencyConverter;

import java.util.Calendar;
import java.util.Scanner;

//не комментирую. если принять изменения в классах моделей, сильно изменится логика тут.

public class AccountService implements IMaintainableService {
    private final AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
    private final BankDaoImpl bankDaoImpl = new BankDaoImpl();
    private final CommissionCalculator commissionCalculator = new CommissionCalculator();
    private final CurrencyConverter currencyConverter = new CurrencyConverter();
    private final ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

    @Override
    public boolean create() {

        Account account = new Account();
        Scanner accountServiceScanner = new Scanner(System.in);

        System.out.println("Введите информацию о счете в порядке: " +
                "имя владельца, банк, валюта(BYN, USD, EUR), сумма\n" +
                "Иван, первый банк, USD, 150");
        System.out.println();
        System.out.println("Банки в наличии:");
        bankDaoImpl.showAllNames();
        System.out.println();

        String accountRaw = accountServiceScanner.nextLine();
        String[] accountS = accountRaw.split(",");

        account.setClientName(accountS[0]);
        if (clientDaoImpl.readByName(accountS[0]) == null) {
            System.out.println("Такого имени клиента не существует. " +
                    "сначала создайте клиента");
            try {
                throw new Exception("no client exist");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            account.setBankName(accountS[1]);


            Currency chosedCurrency = null;
            switch (accountS[2]) {
                case "BYN":
                    chosedCurrency = Currency.BYN;
                    break;
                case "USD":
                    chosedCurrency = Currency.USD;
                    break;
                case "EUR":
                    chosedCurrency = Currency.EUR;
                    break;
                default:
                    System.out.println("Валюта не существует " + accountS[2]);
                    break;
            }

            account.setCurrency(chosedCurrency);
            account.setAmount(Double.parseDouble(accountS[3]));


            accountDaoImpl.create(account);
            System.out.println("Счет на имя " + account.getClientName() + " добавлен");

            return true;
        }
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


    public boolean transferAmount(Account fromA, Account toA, double amount) {
        TransferHistory transferHistory = new TransferHistory();
        TransferHistoryDAO transferHistoryDAO = new TransferHistoryDAO();
        double commission = 0;
        double rate = 1;
        double totalAmount = amount;

        if (!fromA.getBankName().equals(toA.getBankName())) {
            commission = commissionCalculator.calculate(toA, amount);
        }
        if (!fromA.getCurrency().equals(toA.getCurrency())) {
            rate = currencyConverter.returnRate(fromA.getCurrency(), toA.getCurrency(), toA.getBankName());
        }
        if (rate != 1)
            totalAmount = amount * rate;


        fromA.setAmount(fromA.getAmount() - commission - totalAmount);
        toA.setAmount(toA.getAmount() + totalAmount);

        accountDaoImpl.update(fromA);
        accountDaoImpl.update(toA);
        accountDaoImpl.commit();

        transferHistory.setDate(Calendar.getInstance());
        transferHistory.setFromC(fromA.getClientName());
        transferHistory.setToC(toA.getClientName());

        transferHistory.setFromA(fromA);
        transferHistory.setToA(toA);

        transferHistory.setFromB(fromA.getBankName());
        transferHistory.setToB(toA.getBankName());
        transferHistory.setAmount(amount);

        transferHistoryDAO.create(transferHistory);

        return true;
    }


}
