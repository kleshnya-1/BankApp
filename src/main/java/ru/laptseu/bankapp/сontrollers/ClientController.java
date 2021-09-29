package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.services.ClientService;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @RequestMapping("/")
    public void openAll() {
// TODO: 15.09.2021 ask. should this page exist?
    }

    @RequestMapping("/{id}")
    public Client openPage(@PathVariable Integer id) {
        return clientService.read(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public Client newClient(@RequestBody Client client) {
        return clientService.save(client);
    }
}
