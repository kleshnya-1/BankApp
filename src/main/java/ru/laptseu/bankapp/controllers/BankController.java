package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.BankRateList;
import ru.laptseu.bankapp.models.Currency;
import ru.laptseu.bankapp.models.dto.BankDto;
import ru.laptseu.bankapp.models.mappers.BankMapper;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final CurrencyRateService currencyRateService;

    @GetMapping("/")
    public String openAllBanks(Model model) {
        List<BankDto> bankDtos = bankService.read().stream().map(b -> BankMapper.INSTANCE.map(b)).collect(Collectors.toList());
        model.addAttribute("banks", bankDtos);
        return "banks/show";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute BankDto newb) {
        Bank newBank = BankMapper.INSTANCE.map(newb);
        bankService.save(newBank);
        return "redirect:/banks/";
    }

    @GetMapping("/{id}")
    public String openBank(@PathVariable Integer id, Model model) {
        Bank b = bankService.read(id);
        BankDto dt = BankMapper.INSTANCE.map(b);
        model.addAttribute("bank", dt);
        return "banks/showOne";
    }

    @GetMapping("/new_bank")
    public String newBank(@ModelAttribute("newb") BankDto newb) {
        return "banks/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("bank", BankMapper.INSTANCE.map(bankService.read(id)));
        return "banks/edit";
    }

    @GetMapping("/{id}/rates")
    public String rates(Model model, @PathVariable("id") int id) {
        BankRateList bankRateList = currencyRateService.read(id);
        model.addAttribute("bank", BankMapper.INSTANCE.map(bankService.read(id)));
        model.addAttribute("rates", bankRateList);
        try {
            model.addAttribute("ratesList", bankRateList.getCurrenciesAndRates());
        } catch (NullPointerException e) {
            model.addAttribute("ratesList", new HashMap());
        }
        return "banks/rates";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bank") BankDto bankDto) {
        bankService.update(BankMapper.INSTANCE.map(bankDto));
        return "redirect:/banks/";
    }

    @DeleteMapping("/{id}")
    public String deleteBank(@PathVariable Integer id) {
        bankService.delete(id);
        return "redirect:/banks/";
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
        return "banks/new_rate";
    }

    //in progress
    @PostMapping("/{id}/rates")
    public String submit(@PathVariable("id") int id, @ModelAttribute Model model) {
        // Bank newBank = BankMapper.INSTANCE.fromDto(newb);
        // bankService.save(newBank);
        return "redirect:/banks/{id}/";
    }
}
