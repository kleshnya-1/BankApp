package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.repositories.BankRepository;

@Service
@Getter
@RequiredArgsConstructor
public class BankService implements IMaintainableService {
    private final BankRepository dao;
    private final Bank entity;
}
