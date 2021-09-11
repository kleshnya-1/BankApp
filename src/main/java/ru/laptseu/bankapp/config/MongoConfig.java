package ru.laptseu.bankapp.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.laptseu.bankapp.models.CurrencyRate;
import ru.laptseu.bankapp.models.CustomDocument;

import java.util.Set;

@Configuration
//@ComponentScan("ru.laptseu.bankapp")

public class MongoConfig {
    private static final String MONGO_URL = "mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test";

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(MONGO_URL);
    }

    @Bean
    public MongoCollection currencyRatesMongoCollection() {
        return getMongoCollection("CurrencyRates", CustomDocument.class);
    }

    private MongoCollection getMongoCollection(String collectionName, Class clazz) {
        CodecRegistry codecRegistry = codecRegistry();
        com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI(MONGO_URL));
        MongoCollection collection = mongoClient
                .getDatabase("123")
                .withCodecRegistry(codecRegistry).getCollection(collectionName, clazz);
        // mongoClient.close();
        return collection;
    }

    // shouldn't be a bean, isn't it? the same about public.
    private CodecRegistry codecRegistry() {
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
}
