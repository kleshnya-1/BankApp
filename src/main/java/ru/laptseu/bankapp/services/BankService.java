package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.dao.BankDAOImpl;
import ru.laptseu.bankapp.models.Bank;

@Log4j2
@Service
@Getter
@RequiredArgsConstructor
public class BankService implements IMaintainableService<Bank> {
    private final BankDAOImpl dao;

}
