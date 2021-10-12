package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.dto.ClientDto;
import ru.laptseu.bankapp.services.ClientService;


@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    public String openAllClients(Model model) {
        model.addAttribute("clients", clientService.readDto());
        return "clients/show";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute ClientDto newb) {
        clientService.save(clientService.fromDto(newb));
        return "redirect:/clients/";
    }

    @GetMapping("/{id}")
    public String openClient(@PathVariable Integer id, Model model) {
        ClientDto dt = clientService.readDto(id);
        model.addAttribute("client", dt);
        return "clients/showOne";
    }

    @GetMapping("/new_client")
    public String newClient(@ModelAttribute("newb") ClientDto newb) {
        return "clients/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("client", clientService.readDto(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("client") ClientDto clientDto) {
        clientService.update(clientService.fromDto(clientDto));
        return "redirect:/clients/";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.delete(id);
        return "redirect:/clients/";
    }
}
