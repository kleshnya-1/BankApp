package ru.laptseu.bankapp.models;

import lombok.Getter;

@Getter
public enum Currency {
    BYN("byn"),
    USD("usd"),
    EUR("eur");
    private final String label;

    Currency(String label) {
        this.label = label;
    }
}
