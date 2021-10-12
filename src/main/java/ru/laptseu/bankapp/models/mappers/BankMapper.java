package ru.laptseu.bankapp.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.dto.BankDto;

@Mapper(uses = AccountMapper.class)
public interface BankMapper extends MapperInterface{
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);
        BankDto map(Bank bank);

    Bank map(BankDto bankDto);
}
