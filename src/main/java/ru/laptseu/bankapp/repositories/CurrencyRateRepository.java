package ru.laptseu.bankapp.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateListDocument;

@Repository
public interface CurrencyRateRepository extends MongoRepository<BankRateListDocument, ObjectId> {
    BankRateListDocument findFirstByBankIdOrderByDateDesc(int bankId);

    default BankRateListDocument findById(int bankId) {
        return findFirstByBankIdOrderByDateDesc(bankId);
    }
}
