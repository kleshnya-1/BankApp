package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends EntityModel {
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private double amount;
    private String accNumber = Calendar.getInstance().getTime() + "";

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account c = (Account) o;
        return Integer.compare(
                getId(), c.getId()) == 0 &&
                bank.equals(c.bank) &&
                client.equals(c.client) &&
                currency.equals(c.currency) &&
                accNumber.equals(c.accNumber) &&
                amount == c.amount;
    }
}
