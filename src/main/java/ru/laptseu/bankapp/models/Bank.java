package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
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
    @OneToMany
    @Transient
    private List<CurrencyRate> currencyRates = new ArrayList<>();
    // TODO: 15.09.2021 ask у меня есть идея добавить сюда вместе (вместо) поле с документом.
    //  и сделать связь 1-1. но мне кажется, что это увеличит нашу завязанность на монго.

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

