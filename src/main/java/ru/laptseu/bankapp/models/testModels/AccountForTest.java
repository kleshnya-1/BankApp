package ru.laptseu.bankapp.models.testModels;

import lombok.Getter;
import lombok.Setter;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.ModelWithIntegerId;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountForTest extends Account {

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountForTest)) {
            return false;
        }
        AccountForTest c = (AccountForTest) o;
        return Integer.compare(
                getId(), c.getId()) == 0 &&
                bank.equals(c.bank) &&
                client.equals(c.client) &&
                currency.equals(c.currency) &&
                accNumber.equals(c.accNumber) &&
                amount == c.amount;
    }
}
