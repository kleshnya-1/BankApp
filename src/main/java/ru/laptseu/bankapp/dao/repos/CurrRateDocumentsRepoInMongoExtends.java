package ru.laptseu.bankapp.dao.repos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;

//it's not in DAO package and it's a configuration, isn't it?
@Repository
@EnableMongoRepositories(basePackages = "ru.laptseu.bankapp.config")
public interface CurrRateDocumentsRepoInMongoExtends extends MongoRepository<BankRateListDocument, ObjectId> {

    BankRateListDocument findByBankId(int bankId);
    void deleteByBankId(int i);


}
