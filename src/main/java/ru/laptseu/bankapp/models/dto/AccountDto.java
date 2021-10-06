package ru.laptseu.bankapp.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.laptseu.bankapp.models.EntityWithIntegerId;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto extends EntityWithIntegerId {
    private int bankId;
    private int clientId;
    private String currency;
    private double amount;
    private String accNumber;
}
