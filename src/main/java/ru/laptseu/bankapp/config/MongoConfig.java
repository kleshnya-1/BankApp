package ru.laptseu.bankapp.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    // TODO: 14.09.2021 url and database to property
    private static final String MONGO_URL = "mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test";

    @Bean
    public MongoClient mongo() {
        return MongoClients.create(MONGO_URL);
    }

    @Bean
    public MongoCollection currencyRatesMongoCollection()  {
        return mongoTemplate().getCollection("123");
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "123");
    }
}

