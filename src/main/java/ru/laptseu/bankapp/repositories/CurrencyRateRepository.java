package ru.laptseu.bankapp.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.BankRateList;

@Repository
public interface CurrencyRateRepository extends MongoRepository<BankRateList, ObjectId> {
    BankRateList findFirstByBankIdOrderByDateDesc(int bankId);

    default BankRateList findById(int bankId) {
        return findFirstByBankIdOrderByDateDesc(bankId);
    }
}
