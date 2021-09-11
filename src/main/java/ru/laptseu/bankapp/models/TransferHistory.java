package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Log4j2
@Getter
@Setter
@Entity
@Table(name = "transfer_history")
public class TransferHistory extends EntityModel {
    //todo make custom hibernate converter
    //private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Calendar date;
    private String clientSourceName;
    private String clientTargetName;
    private String accSourceNum;
    private String accTargetNum;
    private String bankSourceName;
    private String bankTargetName;
    private String currency;
    private double amount;

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
