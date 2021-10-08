package ru.laptseu.bankapp.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.dto.AccountDto;

@Mapper
public interface AccountMapper extends MapperInterface{

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "client.id", target = "clientId")
    AccountDto map(Account account);

    @Mapping(source = "clientId", target = "client.id")
    Account map(AccountDto accountDTO);
}
