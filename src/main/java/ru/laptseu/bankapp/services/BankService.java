package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.models.mappers.MapperFactory;
import ru.laptseu.bankapp.repositories.BankRepository;
import ru.laptseu.bankapp.repositories.RepositoryFactory;

@Service
@Getter
@RequiredArgsConstructor
public class BankService implements IMaintainableService {
       private final Bank entity;

    private final RepositoryFactory repositoryFactory;
    private final MapperFactory mapperFactory;
    private final ServiceFactory serviceFactory;
}
