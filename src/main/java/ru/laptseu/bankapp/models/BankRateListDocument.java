package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Log4j2
@Getter
@Setter
@Document(collection = "Documents")
public class BankRateListDocument extends EntitySuperModel {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    @BsonProperty("bankId")
    private int bankId;
    private List<CurrencyRate> currencies;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BankRateListDocument)) {
            return false;
        }
        BankRateListDocument c = (BankRateListDocument) o;
        return this.getId().equals(c.getId());
    }
}
