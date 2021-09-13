package ru.laptseu.bankapp.сontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.laptseu.bankapp.models.Bank;
import ru.laptseu.bankapp.services.BankService;

@RestController
@RequestMapping("/banks")
public class BankController {
    //todo in progress
    @Autowired
    private BankService bankService;

    @RequestMapping("/")
    public void openAllBanks(){

    }

    @RequestMapping("/{id}")
    public void openBankPage(@PathVariable Integer id){

    }

    @RequestMapping(method = RequestMethod.POST, value =  "/new")
    public void newBank(@RequestBody Bank bank){
        //save in service

    }
}
