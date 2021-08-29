package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Getter
@Setter
@Entity
@Table(name = "Transfer_History")
public class TransferHistory {
    //todo make custom hibernate converter
    //private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public TransferHistory() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Calendar date;
    private String clientSourceName;
    private String clientTargetName;
    private String accountSourceId;
    private String accountTargetId;
    private String bankSourceName;
    private String bankTargetName;
    private String currency;
    private double amount;
    @Type(type = "yes_no")
    private boolean success;

    public TransferHistory(String clientSourceName, String clientTargetName,
                           String accountSourceId, String accountTargetId, String bankSourceName,
                           String bankTargetName, String currency, double amount) {
        this.date = new GregorianCalendar();
        this.clientSourceName = clientSourceName;
        this.clientTargetName = clientTargetName;
        this.accountSourceId = accountSourceId;
        this.accountTargetId = accountTargetId;
        this.bankSourceName = bankSourceName;
        this.bankTargetName = bankTargetName;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "from acc " + clientSourceName + " with ID " + accountSourceId + " in bank " + bankSourceName + "\n" +
                "to acc " + clientTargetName + " with ID " + accountTargetId + " in bank " + bankTargetName + "\n" +
                date + " transfered " + amount + currency;
    }
}
