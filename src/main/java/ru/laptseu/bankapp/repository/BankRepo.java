package ru.laptseu.bankapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Bank;

@Repository
public interface BankRepo extends CrudRepository<Bank, Integer> {
}
