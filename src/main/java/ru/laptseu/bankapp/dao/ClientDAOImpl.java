package ru.laptseu.bankapp.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import ru.laptseu.bankapp.dao.repos.ClientRepo;
import ru.laptseu.bankapp.models.Client;

@Log4j2
@Getter
@Repository
@RequiredArgsConstructor
public class ClientDAOImpl implements IMaintainableDAO<Client> {

    private final ClientRepo rep;
    private final Client entity = new Client();

}
