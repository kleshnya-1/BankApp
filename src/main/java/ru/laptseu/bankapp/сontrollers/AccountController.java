package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.services.AccountService;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Log4j2
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    //in progress. not for checking
    private final AccountService accountService;
    private final CurrencyRateService currencyRateService;

    @RequestMapping("/")
    public void openAll() {
// TODO: 15.09.2021 ask. should this page exist?
    }

    @RequestMapping("/{id}")
    public Account openPage(@PathVariable Integer id) {
        return accountService.read(id);
    }

    @RequestMapping("/{id}/transfer")
    public int doTransfer(@PathVariable Integer id, @RequestBody Integer targetId, @RequestBody Double amount) {
        return accountService.transferAmount(accountService.read(id), accountService.read(targetId), amount);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public Account newAccount(@RequestBody Account Account) {
        return accountService.save(Account);
    }
}
