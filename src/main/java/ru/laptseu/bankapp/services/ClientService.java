package ru.laptseu.bankapp.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.dto.ClientDto;
import ru.laptseu.bankapp.models.mappers.ClientMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class ClientService extends AbstractService {
    private final Class resourceEntityClass = Client.class;

    @Override
    public List<ClientDto> readDto() {
        List<Client> clientList = (List<Client>) read();
        return clientList.stream().map(entity -> ClientMapper.INSTANCE.map(entity)).collect(Collectors.toList());
    }

    @Override
    public ClientDto readDto(int id) {
        return ClientMapper.INSTANCE.map(read(id));
    }

    public Client fromDto(ClientDto newb) {
        return ClientMapper.INSTANCE.map(newb);
    }


}
