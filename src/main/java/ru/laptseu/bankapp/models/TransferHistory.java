package ru.laptseu.bankapp.models;

import lombok.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
public class TransferHistory {

    //todo по логике запись должна содержать: дату, аккаунтЫ, валюту и сумму
    // перевода, а также банки, в которых эти аккаунты открыты.

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

    public void setDate(Calendar date) {
        format.format(date);
        this.date = date;
    }
}
