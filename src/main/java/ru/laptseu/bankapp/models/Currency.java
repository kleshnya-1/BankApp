package ru.laptseu.bankapp.models;

public enum Currency {
    BYN("byn"),
    USD("usd"),
    EUR("eur");

    public final String label;

    private Currency(String label) {
        this.label = label;
    }

//    public static Currency valueOfLabel(String label) {
//        for (Currency e : values()) {
//            if (e.label.equals(label)) {
//                return e;
//            }
//        }
//        return null;
//    }
}
