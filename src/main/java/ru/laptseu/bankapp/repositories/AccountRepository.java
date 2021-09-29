package ru.laptseu.bankapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account readAccountByAccNumber(String num);
}
