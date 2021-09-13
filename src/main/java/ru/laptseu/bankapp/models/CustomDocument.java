package ru.laptseu.bankapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Getter
@Setter
@Document(collection = "CurrencyRates")
public class CustomDocument {
    //BankRateListDocument failed to decode error
    //todo ask. не могу переименовать. его потом спринг бин не находит. я через shift+f6.
    // при том поиск по проекту нигде кроме закоментированного не находит старого имени.
    /*
    org.bson.codecs.configuration.CodecConfigurationException: Failed to decode 'BankRateListDocument'.
Decoding errored with: A class could not be found for the discriminator: 'ru.laptseu.bankapp.models.CustomDocument'.
...
    A class could not be found for the discriminator: 'ru.laptseu.bankapp.models.CustomDocument'.
    может это быть из-за того, что БД уже принимала КастомДокумент и сейсас не хочет отдавать его с другим названием?
    так как в проекте этого имени больше нет нигде, но он на него ссылается
    */
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    @BsonProperty("bankId")
    private int bankId;
    private List<CurrencyRate> currencies;
}
