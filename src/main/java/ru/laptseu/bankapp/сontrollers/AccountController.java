package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Account;
import ru.laptseu.bankapp.models.dto.AccountDto;
import ru.laptseu.bankapp.models.dto.BankDto;
import ru.laptseu.bankapp.models.dto.ClientDto;
import ru.laptseu.bankapp.models.mappers.AccountMapper;
import ru.laptseu.bankapp.models.mappers.BankMapper;
import ru.laptseu.bankapp.models.mappers.ClientMapper;
import ru.laptseu.bankapp.services.AccountService;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final BankService bankService;
    private final ClientService clientService;

    @GetMapping("/")
    public String openAllAccounts(Model model) {
        List<AccountDto> accountDtos = accountService.read().stream().map(b -> AccountMapper.INSTANCE.toDto(b)).collect(Collectors.toList());
        model.addAttribute("accounts", accountDtos);
        return "accounts/show";
    }

    @PostMapping("/")
    public String submit(@ModelAttribute AccountDto newb) {
        Account newAccount = AccountMapper.INSTANCE.fromDto(newb);
        newAccount.setClient(clientService.read(Integer.valueOf(newb.getClientId())));
        newAccount.setBank(bankService.read(Integer.valueOf(newb.getBankId())));
        accountService.save(newAccount);
        return "redirect:/accounts/";
    }

    @GetMapping("/{id}")
    public String openAccount(@PathVariable Integer id, Model model) {
        Account b = accountService.read(id);
        AccountDto dt = AccountMapper.INSTANCE.toDto(b);
        model.addAttribute("account", dt);
        return "accounts/showOne";
    }

    @GetMapping("/new_account")
    public String newAccount(@ModelAttribute("newb") AccountDto newb, Model bankModel, Model clientModel) {
        List<BankDto> bankDto = bankService.read().stream().map(b -> BankMapper.INSTANCE.toDto(b)).collect(Collectors.toList());
        List<ClientDto> clientDtos = clientService.read().stream().map(b -> ClientMapper.INSTANCE.toDto(b)).collect(Collectors.toList());
        bankModel.addAttribute("bankModel", bankDto);
        clientModel.addAttribute("clientModel", clientDtos);
        return "accounts/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("account", accountService.read(id));
        return "accounts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("account") AccountDto accountDto) {
        accountService.update(AccountMapper.INSTANCE.fromDto(accountDto));
        return "redirect:/accounts/";
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Integer id) {
        accountService.delete(id);
        return "redirect:/accounts/";
    }
}
