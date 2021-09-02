package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank extends EntityModel {
    private String name;
    private double transferFeeInPercent;
    private double transferFeeInPercentForNotNaturalPersons;
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    //todo mongo
    //@OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<CurrencyRate> currencyRates = new HashSet<>();
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        Bank c = (Bank) o;
        return Integer.compare(
                getId(), c.getId()) == 0 &&
                name.equals(c.name) &&
                transferFeeInPercent == c.transferFeeInPercent &&
                transferFeeInPercentForNotNaturalPersons == c.transferFeeInPercentForNotNaturalPersons;
    }
}

