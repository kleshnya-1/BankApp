package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.BankRateList;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.Entity;
import ru.laptseu.bankapp.models.dto.BankDto;
import ru.laptseu.bankapp.models.dto.EntityDto;
import ru.laptseu.bankapp.models.mappers.BankMapper;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final CurrencyRateService currencyRateService;

    @GetMapping("/")
    public String openAllBanks(Model model) {
        List<BankDto> bankDtos = new ArrayList<>();
        for (Entity b : bankService.read()) {
            EntityDto map = BankMapper.INSTANCE.map(b);
            bankDtos.add((BankDto) map);
        }
        model.addAttribute("banks", bankDtos);
        return "banks/show";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute BankDto newb) {
        Bank newBank = (Bank) bankService.fromDto(newb);
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

//    @GetMapping("/{id}/rates")
//    public String rates(Model model, @PathVariable("id") int id) {
//        BankRateList bankRateList = currencyRateService.read(id);
//        model.addAttribute("bank", bankService.readDto(id));
//        try {
//            model.addAttribute("ratesList", bankRateList.getCurrenciesAndRates());
//        } catch (NullPointerException e) {
//            model.addAttribute("ratesList", new HashMap());
//        }
//        return "rates";
//    }

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
