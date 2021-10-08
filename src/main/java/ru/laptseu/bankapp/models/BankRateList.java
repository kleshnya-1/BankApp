package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;


@Getter
@Setter
@Document(collection = "documents")
public class BankRateList extends Entity {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;

    @BsonProperty("bankId")
    private int bankId;

    private Map<Currency, Double> currenciesAndRates;
    private Date date;
}
