package ru.laptseu.bankapp.dao.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.models.Client;
@Repository
public interface ClientRepo extends CrudRepository<Client, Integer> {

}
