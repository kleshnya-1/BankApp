package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.repositories.ClientRepository;

@Service
@Getter
@RequiredArgsConstructor
public class ClientService implements IMaintainableService {
    private final ClientRepository dao;
    private final Client entity;
}
