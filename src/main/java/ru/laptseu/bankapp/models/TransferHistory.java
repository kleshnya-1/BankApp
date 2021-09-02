package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Getter
@Setter
public class TransferHistory {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
