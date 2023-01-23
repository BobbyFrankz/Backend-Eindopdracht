package com.example.backendeindopdracht.service;

import com.example.backendeindopdracht.Models.Account;
import com.example.backendeindopdracht.Repositories.AccountRepository;
import com.example.backendeindopdracht.dtos.AccountDto;
import com.example.backendeindopdracht.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository acRepos;

    public AccountService(AccountRepository acRepos) {
        this.acRepos = acRepos;
    }

    //logica van get mapping all accounts
    public Iterable <AccountDto> getAllAccounts() {
        ArrayList<AccountDto> allDtoAccounts = new ArrayList<AccountDto>();
        Iterable <Account> getAllAccounts = acRepos.findAll();
        for (Account a: getAllAccounts) {
            AccountDto AccountDto = transferToDto(a);
            allDtoAccounts.add(AccountDto);
        }

        return allDtoAccounts;

    }


    //logica van get mapping voor 1 Account
    public AccountDto getOneAccount(Long id) {
        Optional<Account> foundAccount = acRepos.findById(id);

        if (foundAccount.isPresent()) {
            Account account1 = foundAccount.get();
            return transferToDto(account1);
        } else {
            throw new RecordNotFoundException("Account not found");
        }
    }

    // logica van postmapping voor een nieuw account
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = transferFromDto(accountDto);
        Account savedAccount = acRepos.save(account);

        return accountDto;

    }



    //logica van delete mapping
    public String deleteAccount(Long id) {
        Optional<Account> deletedAccount = acRepos.findById(id);
        if (deletedAccount.isPresent()) {
            Account account1 = deletedAccount.get();
            acRepos.delete(account1);
            return "Account Removed";
        } else {
            throw new RecordNotFoundException("Account(ID) not found!");
        }
    }

    //logica van put mapping
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Optional<Account> updatedAccount = acRepos.findById(id);
        if (updatedAccount.isPresent()) {
            // haal de audiofile uit de database
            Account updateAccount = updatedAccount.get();
            // pas de Audio File aan met nieuwe waarde
            updateAccount.setUsername(accountDto.getUsername());
            updateAccount.setPassword(accountDto.getPassword());
            updateAccount.setFirstname(accountDto.getFirstname());
            updateAccount.setLastname(accountDto.getLastname());
            updateAccount.setEmail(accountDto.getEmail());
            updateAccount.setEnabled(accountDto.isEnabled());


            // sla het account op
            acRepos.save(updateAccount);
            //return
            return transferToDto(updateAccount);
        } else {
            throw new RecordNotFoundException("Account(id) not found");
        }

    }

    public AccountDto transferToDto (Account account) {
        AccountDto acdto = new AccountDto();

        acdto.setId(account.getId());
        acdto.setUsername(account.getUsername());
        acdto.setPassword(account.getPassword());
        acdto.setFirstname(account.getFirstname());
        acdto.setLastname(account.getLastname());
        acdto.setEmail(account.getEmail());
        acdto.setEnabled(account.isEnabled());


        return acdto;
    }
    public Account transferFromDto (AccountDto accountDto) {

        Account account = new Account();

        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setEmail(accountDto.getEmail());
        account.setEnabled(accountDto.isEnabled());

        return account;
    }
}
