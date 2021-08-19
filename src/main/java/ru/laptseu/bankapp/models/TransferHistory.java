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
    private Calendar date;
    private Client clientSource;
    private Client clientTarget;
    private Account accountSource;
    private Account accountTarget;
    private Bank bankSource;
    private Bank bankTarget;
    private double amount;
}
