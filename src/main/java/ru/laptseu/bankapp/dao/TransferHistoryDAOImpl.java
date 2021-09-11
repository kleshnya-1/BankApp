package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.TransferHistory;

@Log4j2
@Getter
@Repository
public class TransferHistoryDAOImpl implements IMaintainableDAO<TransferHistory> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public TransferHistory read(int key) {
        return sessionFactory.openSession().get(TransferHistory.class, key);
    }
}
