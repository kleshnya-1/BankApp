package ru.laptseu.bankapp.models;

import lombok.Data;

@Data
public class Account {

    private int id;
    private int bankId;
    //хочу сделать имя банка отдельным классом. изначально
    // там будет только стринг поле, но доступно для расширения.
    // с клиентом аналогично
    private String bankName;
    private String clientName;
    //сумма с валютой на счету может быть и отдельным классом. с добавлением
    // геттеров/сеттеров объекта этого нового отдельного класса (ВАЛЮТЫ и
    // ОСТАТАТКА на счету) в класс аккаунта.
    private Currency currency;
    private double amount;
}
