package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.repos.BankRepo;
import ru.laptseu.bankapp.models.Bank;

@Service
@Getter
@RequiredArgsConstructor
public class BankService implements IMaintainableService<Bank> {
    private final BankRepo dao;
}
