package ru.laptseu.bankapp.dao.repos;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;

@Repository
@Primary
public interface CurrRateDocumentsRepoInMongoExtends extends MongoRepository<BankRateListDocument, ObjectId> {
    BankRateListDocument findFirstByBankIdOrderByDateDesc(int bankId);
}
