package ru.laptseu.bankApp.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransferHistory {
    //этот класс нужен для отображения истории переводов.
    //по логике запись должна содержать: дату, аккаунтЫ, валюту и сумму
    // перевода, а такде банки, в которых эти аккаунты открыты.


    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private int id;
    private Calendar date;
    // С это Client. стоит заменить стринг на класс Сlient-а.
    // с банком и суммой аналогично.
    private String fromC;
    private String toC;
    private Account fromA;
    private Account toA;
    private String fromB;
    private String toB;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDate() {

        return date;
    }

    public void setDate(Calendar date) {
        format.format(date);
        this.date = date;
    }

    public String getFromC() {
        return fromC;
    }

    public void setFromC(String fromC) {
        this.fromC = fromC;
    }

    public String getToC() {
        return toC;
    }

    public void setToC(String toC) {
        this.toC = toC;
    }

    public Account getFromA() {
        return fromA;
    }

    public void setFromA(Account fromA) {
        this.fromA = fromA;
    }

    public Account getToA() {
        return toA;
    }

    public void setToA(Account toA) {
        this.toA = toA;
    }

    public String getFromB() {
        return fromB;
    }

    public void setFromB(String fromB) {
        this.fromB = fromB;
    }

    public String getToB() {
        return toB;
    }

    public void setToB(String toB) {
        this.toB = toB;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
