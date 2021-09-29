package ru.laptseu.bankapp.testModels;

import lombok.Getter;
import lombok.Setter;
import ru.laptseu.bankapp.models.Account;

import javax.persistence.Entity;
import javax.persistence.Table;

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
                getBank().getId() == c.getBank().getId() &&
                getClient().getName().equals(c.getClient().getName()) &&
                getCurrency().equals(c.getCurrency()) &&
                getAccNumber().equals(c.getAccNumber()) &&
                getAmount() == c.getAmount();
    }
}
