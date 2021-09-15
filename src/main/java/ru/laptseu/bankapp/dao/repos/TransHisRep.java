package ru.laptseu.bankapp.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.TransferHistory;
@Repository
public interface TransHisRep extends CrudRepository<TransferHistory, Integer> {
}
