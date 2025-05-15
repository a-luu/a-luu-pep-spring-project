package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    
    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account addAccount(Account account) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(account.getUsername());
        if(optionalAccount.isPresent()){
            return null;
        }else{
            return accountRepository.save(account);
        }
        
        /*List<Account> data = accountRepository.getAllAccounts();
        for (Account a : data) {
            if (a.getAccountId() == account.getAccountId()) {
                return null;
            }
        }
        if (account.getPassword().length() < 4 || account.getUsername() == "")
            return null;
        else
            return accountRepository.insertAccount(account);*/
    }

    public Optional<Account> login(Account account) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(optionalAccount.isPresent()){
            return optionalAccount;
        }else{
            return null;
        }
        //return accountRepository.login(account);
    }

}
