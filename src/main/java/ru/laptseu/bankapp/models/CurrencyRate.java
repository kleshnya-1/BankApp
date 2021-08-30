package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.persistence.*;

@Getter
@Setter
public class CurrencyRate {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    //с hibernate тут банк. а сейчас просто его ИД
    private int bankId;
    private Currency currency;
    private double rateToByn;
}
