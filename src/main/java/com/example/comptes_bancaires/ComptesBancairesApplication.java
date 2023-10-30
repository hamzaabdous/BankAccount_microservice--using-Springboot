package com.example.comptes_bancaires;

import com.example.comptes_bancaires.entities.BankAccount;
import com.example.comptes_bancaires.enums.AccountType;
import com.example.comptes_bancaires.repositories.BankAccountRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class ComptesBancairesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComptesBancairesApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository){
		return args -> {
			for (int i=0;i<10;i++){
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
						.balance(10000+Math.random()*90000)
						.createdAt(new Date())
						.currency("MAD")
						.build();
				bankAccountRepository.save(bankAccount);
			}
		};
	}

}
