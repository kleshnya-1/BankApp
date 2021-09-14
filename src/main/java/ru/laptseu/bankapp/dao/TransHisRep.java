package ru.laptseu.bankapp.dao;

import org.springframework.data.repository.CrudRepository;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.TransferHistory;

public interface TransHisRep extends CrudRepository<TransferHistory, Integer> {

    <S extends TransferHistory> S save(S entity);
}
