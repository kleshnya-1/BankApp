package ru.laptseu.bankApp.models;

import java.util.EnumMap;
import java.util.Map;

public class Bank {
    private int id;
    private String name;
    private double transferFeeInPercent;
    private double transferFeeInPercentForNotNaturalPersons;

    //обменный курс должен быть представлен MAP с валюта(ключ)-курс(знач)
    private double USDrate;
    private double EURrate;
    //как тут планировалось ранее
    private EnumMap<Currency, Double> rateMap = new EnumMap<>(Currency.class);

    public Bank() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTransferFeeInPercentForNotNaturalPersons() {
        return transferFeeInPercentForNotNaturalPersons;
    }

    public void setTransferFeeInPercentForNotNaturalPersons(double transferFeeInPercentForNotNaturalPersons) {
        this.transferFeeInPercentForNotNaturalPersons = transferFeeInPercentForNotNaturalPersons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTransferFeeInPercent() {
        return transferFeeInPercent;
    }

    public void setTransferFeeInPercent(double transferFeeInPercent) {
        this.transferFeeInPercent = transferFeeInPercent;
    }

    public double getUSDrate() {
        return USDrate;
    }

    public void setUSDrate(double USDrate) {
        this.USDrate = USDrate;
    }

    public double getEURrate() {
        return EURrate;
    }

    public void setEURrate(double EURrate) {
        this.EURrate = EURrate;
    }

    public Map<Currency, Double> getRateMap() {
        return rateMap;
    }

    public void setRateMap(EnumMap<Currency, Double> rateMap) {
        this.rateMap = rateMap;
    }
}
