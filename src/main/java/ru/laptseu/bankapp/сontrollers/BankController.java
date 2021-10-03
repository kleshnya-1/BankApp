package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.List;


@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final CurrencyRateService currencyRateService;

    @GetMapping("/")
    public List<Bank> openAllBanks() {
        List<Bank> l = bankService.read();
        l.stream().forEach(b -> b.getAccounts().forEach(account -> {
            account.setBank(null);
            account.setClient(null);
        }));
        return l;
    }

    @PostMapping("/")
    public Bank newBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @GetMapping("/{id}")
    public Bank openBankPage(@PathVariable Integer id) {
        Bank b = bankService.read(id);
        b.getAccounts().forEach(account -> {
            account.setBank(null);
            account.setClient(null);
        });
        return b;// TODO: 03.10.2021 throw something
    }
    // TODO: 03.10.2021 add update

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable Integer id) {
        bankService.delete(id);
    }

    @RequestMapping("/{id}/rates")
    public BankRateListDocument openBankRates(@PathVariable Integer id) {
        //хотя их тоже очень много
        return currencyRateService.read(id);
    }


}
