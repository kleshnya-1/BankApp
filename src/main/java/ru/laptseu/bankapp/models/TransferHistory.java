package ru.laptseu.bankapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Getter
@Setter
@Entity
@Table(name = "transfer_history")
@NoArgsConstructor
@AllArgsConstructor
public class TransferHistory extends EntityWithIntegerId {
    private Calendar date;
    private String clientSourceName;
    private String clientTargetName;
    private String accSourceNum;
    private String accTargetNum;
    private String bankSourceName;
    private String bankTargetName;
    private String currency;
    private double amount;
}
