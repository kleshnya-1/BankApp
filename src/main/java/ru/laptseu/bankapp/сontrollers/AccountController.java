package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.services.AccountService;
import ru.laptseu.bankapp.services.CurrencyRateService;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    //in progress. not for checking
    private final AccountService accountService;

    @RequestMapping("/")
    public void openAll() {
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
