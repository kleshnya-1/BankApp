package ru.laptseu.bankapp.models;

public enum Currency {
    BYN("byn"),
    USD("usd"),
    EUR("eur");

    public final String label;

    Currency(String label) {
        this.label = label;
    }

}
