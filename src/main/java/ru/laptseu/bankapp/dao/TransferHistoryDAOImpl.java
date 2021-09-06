package ru.laptseu.bankapp.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.models.TransferHistory;
import ru.laptseu.bankapp.utilities.HibernateSessionFactoryUtil;

@Log4j2
@Component
public class TransferHistoryDAOImpl implements IMaintainableDAO<TransferHistory> {

    @Override
    public TransferHistory read(int key) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TransferHistory.class, key);
    }

}
