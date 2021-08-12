package ru.laptseu.bankApp.utilities;

import ru.laptseu.bankApp.dao.BankDaoImpl;
import ru.laptseu.bankApp.models.Bank;
import ru.laptseu.bankApp.models.Currency;

public class CurrencyConverter {

    // можно реализовать конвертер в БД. но тогда мы более от нее зависимы.
    // реализовать можно тут. но иначе. я спешил просто там.
    // по моему пониманию транзакция ВСЕГДА будет вызывать конвертер, а
    // вот уже конвертер внутри себя будет дергать утилиту проверки валюты на
    // идентичность и, если они не идентичны, возвращать пересчет в формате класса остатка,
    // а не примитива double

    private final BankDaoImpl bankDaoImpl = new BankDaoImpl();

    public double returnRate(Currency origin, Currency target, String bankName) {
        Bank bank = (Bank) bankDaoImpl.readByName(bankName);
        double rate = 0;
        if (origin == Currency.BYN) {
            if (target == Currency.USD)
                rate = bank.getUSDrate();
            if (target == Currency.EUR)
                rate = bank.getEURrate();
        }
        if (origin == Currency.USD) {
            if (target == Currency.BYN)
                rate = 1 / bank.getUSDrate();
            if (target == Currency.EUR)
                rate = bank.getEURrate() / bank.getUSDrate();
        }
        if (origin == Currency.EUR) {
            if (target == Currency.BYN)
                rate = 1 / bank.getEURrate();
            if (target == Currency.USD)
                rate = bank.getUSDrate() / bank.getEURrate();
        }


        return rate;
    }


}
