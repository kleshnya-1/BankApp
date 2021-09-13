package ru.laptseu.bankapp.utilities;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;

import java.sql.SQLException;

@Component
@Log4j2
public class CommissionCalculator {

    public double calculate(Account targetAcc, double amount) {
        // TODO: 13.09.2021 check for null 
        double commission = 0;
        double commissionPercent = 0;
        Bank targetBank = targetAcc.getBank();
        Client targetClient = targetAcc.getClient();
        if (targetClient.isNaturalPerson()) {
            commissionPercent = targetBank.getTransferFeeInPercent();
        } else {
            commissionPercent = targetBank.getTransferFeeInPercentForNotNaturalPersons();
        }
        commission = commissionPercent * amount / 100;
        log.info("Commission calculated " + commission + targetAcc.getCurrency() + " with percent " + commissionPercent + "% and amount + " + amount);
        return commission;
    }
}
