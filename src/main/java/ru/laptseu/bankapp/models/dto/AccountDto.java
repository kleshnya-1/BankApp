package ru.laptseu.bankapp.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends EntityDto {
    private int id;
    private String bankName;
    private int bankId;
    private String clientName;
    private int clientId;
    private String currency;
    private double amount;
    private String accNumber;
}
