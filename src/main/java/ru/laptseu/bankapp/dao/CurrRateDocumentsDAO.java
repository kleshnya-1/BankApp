package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.dao.repos.CurrRateDocumentsRepoInMongoExtends;
import ru.laptseu.bankapp.models.BankRateListDocument;

//todo проблема с имплиментированием. метод read(int) дальше описание
@Log4j2
@Getter
@Repository
@RequiredArgsConstructor
public class CurrRateDocumentsDAO implements  IMaintainableDAO<BankRateListDocument>{
    private final CurrRateDocumentsRepoInMongoExtends rep;
    private final BankRateListDocument entity = new BankRateListDocument();
}
