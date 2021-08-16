package ru.laptseu.bankapp.models;
import lombok.Data;
import java.util.EnumMap;
@Data
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
}
