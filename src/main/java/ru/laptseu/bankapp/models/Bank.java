package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private double transferFeeInPercent;
    private double transferFeeInPercentForNotNaturalPersons;


    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CurrencyRate> currencyRates = new ArrayList<>();
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    public void addRate(CurrencyRate currencyRate){
        currencyRate.setBank(this);
        currencyRates.add(currencyRate);
    }
    public void addAccount(Account account){
        account.setBank(this);
        accounts.add(account);
    }

    //todo ask я же правильно понимаю, что он при таком запросе,пойдет в БД, возьмет вест лист
    // и из него достанет уже нам нужное? еще и перебором каждого. может, лучше через дао HQL и ORDER BY прописать? тогда
    // и лист на сет поменять можно. и я не встречал, чтоб удет с ИД при удалении старых, их перезапишет
    // и тогда нужен будет иной инструмент, чем упорядочивание ИД. нужно будет ввести дату.
    public CurrencyRate getLastCurrency(Currency curr){
        return currencyRates.stream().filter(currencyRate -> currencyRate.getCurrency().equals(curr))
                .reduce((first, second) -> second).orElse(null);
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        Bank c = (Bank) o;
        return Integer.compare(id, c.id) == 0
                && name.equals(c.name);
    }
}

