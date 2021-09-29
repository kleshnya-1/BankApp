package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.repositories.BankRepository;

@Service
@Getter
@RequiredArgsConstructor
public class BankService implements IMaintainableService<Bank> {
    private final BankRepository dao;
}
