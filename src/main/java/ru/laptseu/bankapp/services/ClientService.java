package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.repositories.ClientRepo;

@Service
@Getter
@RequiredArgsConstructor
public class ClientService implements IMaintainableService<Client> {
    private final ClientRepo dao;
}
