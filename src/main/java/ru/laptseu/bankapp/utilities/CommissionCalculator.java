package ru.laptseu.bankapp.utilities;

import ru.laptseu.bankapp.dao.BankDaoImpl;
import ru.laptseu.bankapp.dao.ClientDaoImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

public class CommissionCalculator {


    public double calculate(Account second, double amount) {
        BankDaoImpl bankDaoImpl = new BankDaoImpl();
        ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
        double commission = 0;
        Bank secondB = (Bank) bankDaoImpl.readByName(second.getBankName());
        Client sCl = (Client) clientDaoImpl.readByName(second.getClientName());
        if (sCl.isNaturalPerson() == true)
            commission = secondB.getTransferFeeInPercent() * amount / 100;
        else
            commission = secondB.getTransferFeeInPercentForNotNaturalPersons() * amount / 100;
        return commission;
    }
}
