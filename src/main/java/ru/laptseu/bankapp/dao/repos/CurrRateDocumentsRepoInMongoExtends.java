package ru.laptseu.bankapp.dao.repos;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;

@Repository
@EnableMongoRepositories(basePackages = "ru.laptseu.bankapp.config")
public interface CurrRateDocumentsRepoInMongoExtends extends MongoRepository<BankRateListDocument, ObjectId> {
    //не костыль, просто на ид сущностей не интересует. мы ищем по ИД банка
    BankRateListDocument findByBankId(int bankId);
}
