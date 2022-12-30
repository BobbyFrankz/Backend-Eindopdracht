package com.example.backendeindopdracht.Controllers;

import com.example.backendeindopdracht.dtos.AccountDto;
import com.example.backendeindopdracht.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/accounts")
    public Iterable<AccountDto> getAllAccounts() {

        var dtos = accountService.getAllAccounts();

        return dtos;
    }

    @GetMapping("/accounts/{id}")
    public AccountDto getOneAccount(@PathVariable("id") Long id) {

        AccountDto accountDto = accountService.getOneAccount(id);

        return accountDto;
    }

    @PostMapping("/accounts")
    public AccountDto createAccount(@RequestBody AccountDto dto) {
        AccountDto accountDto = accountService.createAccount(dto);
        return accountDto;
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/accounts/{id}")
    public AccountDto updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {
        accountService.updateAccount(id, accountDto);
        return accountDto;
    }

}
