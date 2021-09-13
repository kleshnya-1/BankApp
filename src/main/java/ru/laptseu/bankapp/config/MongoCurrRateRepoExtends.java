package ru.laptseu.bankapp.config;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.CustomDocument;

//it's not in DAO package and it's a configuration, isn't it?
@Repository
@EnableMongoRepositories(basePackages = "ru.laptseu.bankapp.config")
public interface MongoCurrRateRepoExtends extends MongoRepository<CustomDocument, ObjectId> {
    CustomDocument findByBankId(int bankId);
}
