package ru.laptseu.bankapp.dao;

import org.springframework.stereotype.Component;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.models.CustomDocument;

import java.util.List;
@Component
public abstract class NiceDao implements CurrRateDocumentsRepoInMongoExtends {

    @Override
    public CustomDocument findByBankId(int bankId) {
        return null;
    }

    @Override
    public void deleteByBankId(int i) {

    }
}
