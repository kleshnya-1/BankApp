package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@Getter
@Setter
public class CustomDocument {
    //todo ask. не могу переименовать. его потом спринг бин не находит. я через shift+f6.
    // при том поиск по проекту нигде кроме закоментированного не находит старого имени.
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    @BsonProperty("bankId")
    private int bankId;
    private List<CurrencyRate> currencies;
}
