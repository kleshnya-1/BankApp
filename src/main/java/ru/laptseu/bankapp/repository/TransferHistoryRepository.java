package ru.laptseu.bankapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.TransferHistory;

@Repository
public interface TransferHistoryRepository extends CrudRepository<TransferHistory, Integer> {
}
