package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.models.BankRateListDocument;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

@Log4j2
@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final CurrencyRateService currencyRateService;

    @RequestMapping("/")
    public void openAllBanks() {
// TODO: 15.09.2021 ask. should this page exist?
    }

    @RequestMapping("/{id}")
    public Bank openBankPage(@PathVariable Integer id) {
            return bankService.read(id);
    }

    @RequestMapping("/{id}/rates")
    public BankRateListDocument openBankRates(@PathVariable Integer id) {
        //хотя их тоже очень много
        return currencyRateService.read(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public Bank newBank(@RequestBody Bank bank) {
       return bankService.save(bank);
    }
    // TODO: 15.09.2021 остальные контроллеры будут по такому же принципу. еще будет клиент и админ.
    //  вся разница в реализации их полномочий и поступных страниц.
    //  я еще ссылки хотел сделать, чтоб в браузере можно было переходить с одной страницы на другую без адресной строки
}
