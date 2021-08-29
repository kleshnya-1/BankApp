package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

import javax.persistence.*;

@Getter
@Setter
public class CurrencyRate {
    private int id;
    //с hibernate тут банк. а сейчас просто его ИД
    private int bankId;
    private Currency currency;
    private double rateToByn;

}
