package ru.laptseu.bankapp.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.dto.AccountDto;

@Mapper(uses = {BankMapper.class, ClientMapper.class})
public interface AccountMapper extends MapperInterface {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "bank.name", target = "bankName")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")
    AccountDto map(Account account);

    @Mapping(source = "clientId", target = "client.id")
    Account map(AccountDto accountDTO);
}
