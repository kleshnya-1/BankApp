package ru.laptseu.bankapp.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.laptseu.bankapp.models.EntityWithIntegerId;

@Getter
@Setter

@NoArgsConstructor
public class ClientDto extends EntityWithIntegerId {
    private String name;
    private boolean isNaturalPerson;
}
