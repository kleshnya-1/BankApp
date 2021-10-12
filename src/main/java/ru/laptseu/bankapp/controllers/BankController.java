package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.dto.BankDto;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.List;


@Controller
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final CurrencyRateService currencyRateService;

    @GetMapping("/")
    public String openAllBanks(Model model) {
        List<BankDto> bankDtos = bankService.readDto();
        model.addAttribute("banks", bankDtos);
        return "banks/show";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute BankDto newb) {
        Bank newBank = bankService.fromDto(newb);
        bankService.save(newBank);
        return "redirect:/banks/";
    }

    @GetMapping("/{id}")
    public String openBank(@PathVariable Integer id, Model model) {
        BankDto dt = bankService.readDto(id);
        model.addAttribute("bank", dt);
        return "banks/showOne";
    }

    @GetMapping("/new_bank")
    public String newBank(@ModelAttribute("newb") BankDto newb) {
        return "banks/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("bank", bankService.readDto(id));
        return "banks/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bank") BankDto bankDto) {
        bankService.update(bankService.fromDto(bankDto));
        return "redirect:/banks/";
    }

    @DeleteMapping("/{id}")
    public String deleteBank(@PathVariable Integer id) {
        bankService.delete(id);
        return "redirect:/banks/";
    }

}
