package ru.laptseu.bankapp.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.dto.BankDto;

@Mapper(uses = AccountMapper.class)
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankDto toDto(Bank bank);

    Bank fromDto(BankDto bankDto);
}
