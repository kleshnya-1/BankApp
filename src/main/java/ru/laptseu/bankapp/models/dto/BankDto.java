package ru.laptseu.bankapp.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.laptseu.bankapp.models.EntityWithIntegerId;


@Getter
@Setter

@NoArgsConstructor
public class BankDto extends EntityDto{
    private String name;
    private double transferFeeInPercent;
    private double transferFeeInPercentForNotNaturalPersons;
}

