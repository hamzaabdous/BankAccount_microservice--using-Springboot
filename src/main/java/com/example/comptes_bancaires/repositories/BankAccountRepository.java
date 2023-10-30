package com.example.comptes_bancaires.repositories;

import com.example.comptes_bancaires.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository <BankAccount,String>{
}
