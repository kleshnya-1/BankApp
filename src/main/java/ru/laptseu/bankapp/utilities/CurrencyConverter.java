//package ru.laptseu.bankapp.utilities;
//
//import ru.laptseu.bankapp.dao.BankDAOImpl;
//import ru.laptseu.bankapp.models.Bank;
//import ru.laptseu.bankapp.models.Currency;
//
//import java.sql.SQLException;
//
//public class CurrencyConverter {
//
//    // todo можно реализовать конвертер в БД. но тогда мы более от нее зависимы.
//    //  реализовать можно тут. но иначе. я спешил просто там.
//    //  по моему пониманию транзакция ВСЕГДА будет вызывать конвертер,
//    //  вот уже конвертер внутри себя будет дергать утилиту проверки валюты на идентичность и,
//    //  если они не идентичны, возвращать пересчет в формате класса остатка,
//    //  а не примитива double
//
//    private final BankDAOImpl bankDaoImpl = new BankDAOImpl();
//
//    public double returnRate(Currency origin, Currency target, String bankName) {
//        Bank bank = null;
//        try {
//            bank = bankDaoImpl.readByName(bankName);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        double rate = 0;
//        if (origin == Currency.BYN) {
//            if (target == Currency.USD)
//                rate = bank.getUSDrate();
//            if (target == Currency.EUR)
//                rate = bank.getEURrate();
//        }
//        if (origin == Currency.USD) {
//            if (target == Currency.BYN)
//                rate = 1 / bank.getUSDrate();
//            if (target == Currency.EUR)
//                rate = bank.getEURrate() / bank.getUSDrate();
//        }
//        if (origin == Currency.EUR) {
//            if (target == Currency.BYN)
//                rate = 1 / bank.getEURrate();
//            if (target == Currency.USD)
//                rate = bank.getUSDrate() / bank.getEURrate();
//        }
//        return rate;
//    }
//}
