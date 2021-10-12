package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.BankRateList;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.dto.BankDto;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.HashMap;


@Controller
@RequestMapping("/rates")
@RequiredArgsConstructor
public class RateController {
    private final BankService bankService;
    private final CurrencyRateService currencyRateService;


    @GetMapping("/{id}")
    public String openRateForBank(@PathVariable Integer id, Model model) {
        BankRateList bankRateList = currencyRateService.read(id);
        model.addAttribute("bank", bankService.readDto(id));
        try {
            model.addAttribute("ratesList", bankRateList.getCurrenciesAndRates());
        } catch (NullPointerException e) {
            model.addAttribute("ratesList", new HashMap());
        }
        return "rates/rates";
    }
    //in progress below
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("bank", bankService.readDto(id));
        return "rates/edit";
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

    //in progress
    @GetMapping("/new_rate")
    public String newRate(@ModelAttribute("newb") BankDto newb) {
        return "rates/new_rate";
    }
    //in progress
    @GetMapping("/{id}/rates/new_rate")
    public String addRates(@ModelAttribute("currency") Currency currency,
                           @ModelAttribute("rate") Double rate,
                           Model model,
                           @PathVariable("id") int id) {
        BankRateList bankRateList = currencyRateService.read(id);
        model.addAttribute("bankId", id);
        model.addAttribute("mapKey", "");
        model.addAttribute("mapValue", new Double(0.0));
        return "new_rate";
    }

    //in progress
    @PostMapping("/{id}/rates")
    public String submit(@PathVariable("id") int id, @ModelAttribute Model model) {
        // Bank newBank = BankMapper.INSTANCE.fromDto(newb);
        // bankService.save(newBank);
        return "redirect:/rates/{id}/";
    }
}
