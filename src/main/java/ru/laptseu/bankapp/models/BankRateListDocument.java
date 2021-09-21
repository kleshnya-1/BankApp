package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Log4j2
@Getter
@Setter
@Document(collection = "Documents")
public class BankRateListDocument extends Model {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;

    @BsonProperty("bankId")
    private int bankId;
    private Map<Currency, Double> currenciesAndRates;
    private Date date;
}
