//package ru.laptseu.bankapp.services;
//
//import ru.laptseu.bankapp.dao.AccountDAOImpl;
//import ru.laptseu.bankapp.dao.BankDAOImpl;
//import ru.laptseu.bankapp.dao.ClientDAOImpl;
//import ru.laptseu.bankapp.dao.TransferHistoryDAOImpl;
//import ru.laptseu.bankapp.models.Account;
//import ru.laptseu.bankapp.models.Currency;
//import ru.laptseu.bankapp.models.TransferHistory;
//import ru.laptseu.bankapp.utilities.CommissionCalculator;
//
//import java.util.Calendar;
//import java.util.Scanner;
//
////не комментирую. если принять изменения в классах моделей, сильно изменится логика тут.
//
//public class AccountService implements IMaintainableService {
//    private final AccountDAOImpl accountDaoImpl = new AccountDAOImpl();
//    private final BankDAOImpl bankDaoImpl = new BankDAOImpl();
//    private final CommissionCalculator commissionCalculator = new CommissionCalculator();
//    //private final CurrencyConverter currencyConverter = new CurrencyConverter();
//    private final ClientDAOImpl clientDaoImpl = new ClientDAOImpl();
//
//    @Override
//    public boolean create() {
//
//        Account account = new Account();
//        Scanner accountServiceScanner = new Scanner(System.in);
//
//
//      //  bankDaoImpl.showAllNames();
//        System.out.println();
//
//        String accountRaw = accountServiceScanner.nextLine();
//        String[] accountS = accountRaw.split(",");
//
//        account.setClientName(accountS[0]);
//        if (clientDaoImpl.readByName(accountS[0]) == null) {
//            System.out.println("Такого имени клиента не существует. " +
//                    "сначала создайте клиента");
//            try {
//                throw new Exception("no client exist");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            account.setBankName(accountS[1]);
//            Currency chosedCurrency = null;
//            switch (accountS[2]) {
//                case "BYN":
//                    chosedCurrency = Currency.BYN;
//                    break;
//                case "USD":
//                    chosedCurrency = Currency.USD;
//                    break;
//                case "EUR":
//                    chosedCurrency = Currency.EUR;
//                    break;
//                default:
//                    System.out.println("Валюта не существует " + accountS[2]);
//                    break;
//            }
//            account.setCurrency(chosedCurrency);
//            account.setAmount(Double.parseDouble(accountS[3]));
//            accountDaoImpl.create(account);
//            System.out.println("Счет на имя " + account.getClientName() + " добавлен");
//            return true;
//        }
//        return false;
//
//    }
//
//    @Override
//    public boolean read() {
//        return false;
//    }
//
//    @Override
//    public boolean update() {
//        return false;
//    }
//
//    @Override
//    public boolean delete() {
//        return false;
//    }
//
//    public boolean transferAmount(Account fromA, Account toA, double amount) {
//        TransferHistory transferHistory = new TransferHistory();
//        TransferHistoryDAOImpl transferHistoryDAOImpl = new TransferHistoryDAOImpl();
//        double commission = 0;
//        double rate = 1;
//        double totalAmount = amount;
//        if (!fromA.getBankName().equals(toA.getBankName())) {
//            commission = commissionCalculator.calculate(toA, amount);
//        }
//        if (!fromA.getCurrency().equals(toA.getCurrency())) {
//           // rate = currencyConverter.returnRate(fromA.getCurrency(), toA.getCurrency(), toA.getBankName());
//        }
//        if (rate != 1)
//            totalAmount = amount * rate;
//
//        fromA.setAmount(fromA.getAmount() - commission - totalAmount);
//        toA.setAmount(toA.getAmount() + totalAmount);
//
//        accountDaoImpl.update(fromA);
//        accountDaoImpl.update(toA);
//       // accountDaoImpl.commit();
//
//        transferHistory.setDate(Calendar.getInstance());
//        transferHistory.setFromC(fromA.getClientName());
//        transferHistory.setToC(toA.getClientName());
//        transferHistory.setFromA(fromA);
//        transferHistory.setToA(toA);
//        transferHistory.setFromB(fromA.getBankName());
//        transferHistory.setToB(toA.getBankName());
//        transferHistory.setAmount(amount);
//        transferHistoryDAOImpl.create(transferHistory);
//
//        return true;
//    }
//
//}
