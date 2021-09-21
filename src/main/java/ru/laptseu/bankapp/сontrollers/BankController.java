package ru.laptseu.bankapp.сontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.services.BankService;
import ru.laptseu.bankapp.services.CurrencyRateService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {
    // TODO: 15.09.2021 info. сюда ошибки долетают. если ошибка, он возвращает пустые объекты. хотя на https://spring.io/ я вижу,
    //  что они сюда кидают ошибки из методов сервисов и потом их дальше бросают. мне стоит тоже так же сделать?

    private final BankService bankService;
    private final CurrencyRateService currencyRateService;

    @RequestMapping("/")
    public void openAllBanks() {
// TODO: 15.09.2021 ask. надо? в теории их же может быть очень много и такой объем информации.
//  обычному пользователю оно не надо. это для администратора.
    }

    @RequestMapping("/{id}")
    public Bank openBankPage(@PathVariable Integer id) {
        try {
            return bankService.read(id);
        } catch (Throwable e) {
            log.error(e);
            // TODO: 15.09.2021 что-то сделать с шибкой
            return null;
        }
    }

    @RequestMapping("/{id}/rates")
    public List<CurrencyRate> openBankRates(@PathVariable Integer id) throws Throwable {
        //хотя их тоже очень много
        return currencyRateService.read(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/new")
    public void newBank(@RequestBody Bank bank) {
        bankService.save(bank);
    }
    // TODO: 15.09.2021 остальные контроллеры будут по такому же принципу. еще будет клиент и админ.
    //  вся разница в реализации их полномочий и поступных страниц.
    //  я еще ссылки хотел сделать, чтоб в браузере можно было походить без постмана.
    //  но я по гайдам понял, что так не делают уже
}
