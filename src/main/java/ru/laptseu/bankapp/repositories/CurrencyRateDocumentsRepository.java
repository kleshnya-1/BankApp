package ru.laptseu.bankapp.repositories;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;

@Repository
@Primary//because of adapter
public interface CurrencyRateDocumentsRepository extends MongoRepository<BankRateListDocument, ObjectId> {
    BankRateListDocument findFirstByBankIdOrderByDateDesc(int bankId);
}
