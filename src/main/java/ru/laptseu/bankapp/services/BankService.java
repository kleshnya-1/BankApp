package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.dto.BankDto;
import ru.laptseu.bankapp.models.mappers.BankMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class BankService extends AbstractService {
    private final Class resourceEntityClass = Bank.class;

    @Override
    public List<BankDto> readDto() {
        List<Bank> bankList = (List<Bank>) read();
        return bankList.stream().map(entity -> BankMapper.INSTANCE.map(entity)).collect(Collectors.toList());
    }

    @Override
    public BankDto readDto(int id) {
        return BankMapper.INSTANCE.map(read(id));
    }

    public Bank fromDto(BankDto newb) {
        return BankMapper.INSTANCE.map(newb);
    }
}
