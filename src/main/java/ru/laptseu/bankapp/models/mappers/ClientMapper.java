package ru.laptseu.bankapp.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.dto.ClientDto;

@Mapper(uses = {AccountMapper.class, ClientMapper.class})
public interface ClientMapper extends MapperInterface {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto map(Client client);

    Client map(ClientDto clientDto);
}
