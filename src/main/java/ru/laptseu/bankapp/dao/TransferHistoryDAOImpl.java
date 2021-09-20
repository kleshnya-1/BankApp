package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.dao.repos.TransferHistoryRepository;
import ru.laptseu.bankapp.models.TransferHistory;

@Log4j2
@Getter
@Repository
@RequiredArgsConstructor
public class TransferHistoryDAOImpl implements IMaintainableDAO<TransferHistory> {
    private final TransferHistoryRepository rep;
    private final TransferHistory entity = new TransferHistory();
}
