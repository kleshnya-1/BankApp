package ru.laptseu.bankapp.testModels;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Bank;

import javax.persistence.Entity;
import javax.persistence.Table;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "banks")
public class BankForTest extends Bank {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BankForTest)) {
            return false;
        }
        BankForTest c = (BankForTest) o;
        return Integer.compare(
                getId(), c.getId()) == 0 &&
                getName().equals(c.getName()) &&
                getTransferFeeInPercent() == c.getTransferFeeInPercent() &&
                getTransferFeeInPercentForNotNaturalPersons() == c.getTransferFeeInPercentForNotNaturalPersons();
    }
}

