package ru.laptseu.bankapp.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends EntityDto {
    private int id;
    private String name;
    private boolean isNaturalPerson;
}
