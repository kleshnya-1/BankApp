package ru.laptseu.bankapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Type;

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
@Table(name = "clients")
public class Client extends ModelWithIntegerId {
    private String name;
    @Type(type = "yes_no")
    private boolean isNaturalPerson;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Account> accounts = new HashSet<>();
}
