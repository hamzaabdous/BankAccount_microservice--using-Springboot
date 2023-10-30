package com.example.comptes_bancaires.web;

import com.example.comptes_bancaires.entities.BankAccount;
import com.example.comptes_bancaires.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    @GetMapping("/bankaccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankaccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankaccounts/")
    public BankAccount save(@RequestBody BankAccount bankAccount){
        if (bankAccount.getId()==null)bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }
    @PutMapping("/bankaccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankaccounts/{id}")
    public void deleteAccount (@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
