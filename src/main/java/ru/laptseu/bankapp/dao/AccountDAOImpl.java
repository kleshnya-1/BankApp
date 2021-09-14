package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.dao.repos.AccountRepo;
import ru.laptseu.bankapp.models.Account;

@Log4j2
@Getter
@Repository
@RequiredArgsConstructor
public class AccountDAOImpl implements IMaintainableDAO<Account>{

    private final AccountRepo rep;
    private final Account entity = new Account();
}
