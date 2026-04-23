package org.example.banksimulation.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChaosController {

    @PostMapping("/chaos")
    public void chaos() {
        System.exit(0);
    }
}