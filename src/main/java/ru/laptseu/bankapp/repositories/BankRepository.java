package ru.laptseu.bankapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, Integer> {
}
