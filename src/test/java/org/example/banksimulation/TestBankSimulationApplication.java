package org.example.banksimulation;

import org.springframework.boot.SpringApplication;

public class TestBankSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.from(BankSimulationApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
