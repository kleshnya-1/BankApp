package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.dao.ClientDAOImpl;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

import java.sql.SQLException;

@Log4j2
public class CommissionCalculator {

    public double calculate(Account targetAcc, double amount) throws SQLException {
        double commission = 0;
        double commissionPercent = 0;
        Bank targetBank = targetAcc.getBank();//bankDaoImpl.read(targetAcc.getBankId());
        Client targetClient = targetAcc.getClient();
        if (targetClient.isNaturalPerson()) commissionPercent = targetBank.getTransferFeeInPercent();
        else commissionPercent = targetBank.getTransferFeeInPercentForNotNaturalPersons();
        commission = commissionPercent * amount / 100;
        log.info("Commission calculated " + commission + targetAcc.getCurrency()+" with percent " + commissionPercent + "% and amount + " + amount);
        return commission;
    }
}
