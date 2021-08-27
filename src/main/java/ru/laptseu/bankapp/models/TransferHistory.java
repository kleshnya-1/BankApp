package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Entity
@Table(name = "Transfer_History")
public class TransferHistory {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String clientSourceName;
    private String clientTargetName;
    private String accountSourceName;
    private String accountTargetName;
    private String bankSourceName;
    private String bankTargetName;
    private String currency;
    private double amount;
}
