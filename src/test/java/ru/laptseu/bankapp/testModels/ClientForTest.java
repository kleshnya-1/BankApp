package ru.laptseu.bankapp.testModels;

import lombok.Getter;
import lombok.Setter;
import ru.laptseu.bankapp.models.Client;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "clients")
public class ClientForTest extends Client {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ClientForTest)) {
            return false;
        }
        ClientForTest c = (ClientForTest) o;
        return Integer.compare(
                getId(), c.getId()) == 0 &&
                getName().equals(c.getName()) &&
                isNaturalPerson() == c.isNaturalPerson();
    }
}
