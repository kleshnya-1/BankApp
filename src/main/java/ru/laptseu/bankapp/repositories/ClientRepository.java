package ru.laptseu.bankapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
