package ru.laptseu.bankapp.models;


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


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
