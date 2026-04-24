package org.example.banksimulation.controllers;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChaosController {

    private final ConfigurableApplicationContext context;

    public ChaosController(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/chaos")
    public void  chaos() {
        context.close();
        
    }
}