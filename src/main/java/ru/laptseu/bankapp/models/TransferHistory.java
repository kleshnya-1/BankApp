package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Getter
@Setter
@Entity
@Table(name = "Transfer_History")
public class TransferHistory {
    //todo make custom hibernate converter
    //private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Calendar date;
    private String clientSourceName;
    private String clientTargetName;
    private String accSourceNum;
    private String accTargetNum;
    private String bankSourceName;
    private String bankTargetName;
    private String currency;
    private double amount;
    @Type(type = "yes_no")
    private boolean success;

    public TransferHistory() {
    }

    public TransferHistory(String clientSourceName, String clientTargetName,
                           String accSourceNum, String accTargetNum, String bankSourceName,
                           String bankTargetName, String currency, double amount) {
        this.date = new GregorianCalendar();
        this.clientSourceName = clientSourceName;
        this.clientTargetName = clientTargetName;
        this.accSourceNum = accSourceNum;
        this.accTargetNum = accTargetNum;
        this.bankSourceName = bankSourceName;
        this.bankTargetName = bankTargetName;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "from acc " + clientSourceName + " with № " + accSourceNum + " in bank " + bankSourceName + "\n" +
                "to acc " + clientTargetName + " with № " + accTargetNum + " in bank " + bankTargetName + "\n" +
                date + " transfered " + amount + currency;
    }
}
