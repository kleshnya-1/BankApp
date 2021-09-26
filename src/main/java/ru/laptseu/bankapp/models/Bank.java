package ru.laptseu.bankapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank extends ModelWithIntegerId {
    private String name;
    private double transferFeeInPercent;
    private double transferFeeInPercentForNotNaturalPersons;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Account> accounts = new HashSet<>();
}

