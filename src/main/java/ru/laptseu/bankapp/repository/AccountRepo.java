package ru.laptseu.bankapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Account;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
    Account readAccountByAccNumber(String num);
}
