package ru.laptseu.bankapp.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.CurrencyRateBankList;

@Repository
public interface CurrencyRateRepository extends MongoRepository<CurrencyRateBankList, ObjectId> {
    CurrencyRateBankList findFirstByBankIdOrderByDateDesc(int bankId);

    default CurrencyRateBankList findById(int bankId) {
        return findFirstByBankIdOrderByDateDesc(bankId);
    }
}
