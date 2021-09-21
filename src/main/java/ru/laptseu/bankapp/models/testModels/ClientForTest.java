package ru.laptseu.bankapp.models.testModels;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.ModelWithIntegerId;

import javax.persistence.Entity;
import javax.persistence.Table;

@Log4j2
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
                name.equals(c.name) &&
                isNaturalPerson == c.isNaturalPerson;
    }
}
