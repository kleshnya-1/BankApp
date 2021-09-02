package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity

@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Type(type = "yes_no")
    private boolean isNaturalPerson;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();

    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }
}
