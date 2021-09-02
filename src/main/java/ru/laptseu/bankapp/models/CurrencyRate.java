package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.persistence.ManyToOne;

@Getter
@Setter
//@Entity
//todo in progress. road to NoSQL
public class CurrencyRate extends EntityModel {

    @ManyToOne
    Bank bank;
    @BsonProperty("_id")
    @BsonId

    private ObjectId id;
    private Currency currency;
    private double rateToByn;

    public int getId() {
        return -500;
    }
}
