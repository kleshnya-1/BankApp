package ru.laptseu.bankapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.laptseu.bankapp.models.CurrencyRateBankList;
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
        CurrencyRateBankList currencyRateBankList = currencyRateService.read(id);
        model.addAttribute("bank", bankService.readDto(id));
        try {
            model.addAttribute("ratesList", currencyRateBankList.getCurrenciesAndRates());
        } catch (NullPointerException e) {
            model.addAttribute("ratesList", new HashMap());
        }
        return "rates/rates";
    }

}
