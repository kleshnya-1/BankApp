package ru.laptseu.bankapp.core;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.CustomDocument;

import java.util.Set;

//todo это последний вариант реализации. с постгрес рабочий вариант у меня есть и конфигурация там поднимается.
// он в SpringConfig, но тут отключен. а с монго никак
@Component
public class MyBean {
    private static final String MONGO_URL = "mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test";
    private final MongoDatabaseFactory mongo;
    MongoProperties mongoProperties = new MongoProperties();

    public MyBean(MongoDatabaseFactory mongo) {
        // mongo.
        this.mongo = mongo;
    }

    private static CodecRegistry getCodeсRegistry() {

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build(),
                                ClassModel.builder(Set.class).enableDiscriminator(true).build(),
                                ClassModel.builder(CustomDocument.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );
        return codecRegistry;
    }

    public MongoCollection getMongoCollection(String collectionName, Class clazz) {
        CodecRegistry codecRegistry = getCodeсRegistry();
        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URL));
        MongoCollection collection = mongoClient
                .getDatabase("123")
                .withCodecRegistry(codecRegistry).getCollection(collectionName, clazz);
        return collection;
    }
}



