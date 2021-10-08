package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Client;
import ru.laptseu.bankapp.models.dto.ClientDto;
import ru.laptseu.bankapp.models.mappers.ClientMapper;
import ru.laptseu.bankapp.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    public String openAllClients(Model model) {
        List<ClientDto> clientDtos = clientService.read().stream().map(b -> ClientMapper.INSTANCE.map(b)).collect(Collectors.toList());
        model.addAttribute("clients", clientDtos);
        return "clients/show";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute ClientDto newb) {
        Client newClient = ClientMapper.INSTANCE.map(newb);
        clientService.save(newClient);
        return "redirect:/clients/";
    }

    @GetMapping("/{id}")
    public String openClient(@PathVariable Integer id, Model model) {
        Client b = clientService.read(id);
        ClientDto dt = ClientMapper.INSTANCE.map(b);
        model.addAttribute("client", dt);
        return "clients/showOne";
    }

    @GetMapping("/new_client")
    public String newClient(@ModelAttribute("newb") ClientDto newb) {
        return "clients/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientService.read(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") ClientDto clientDto) {
        clientService.update(ClientMapper.INSTANCE.map(clientDto));
        return "redirect:/clients/";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.delete(id);
        return "redirect:/clients/";
    }
}
