package org.example.banksimulation.controllers;

import org.example.banksimulation.dto.GetStocksResponse;
import org.example.banksimulation.dto.SetStocksRequest;
import org.example.banksimulation.services.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/stocks")
    public ResponseEntity<Void> setStocks(@RequestBody SetStocksRequest request) {
        bankService.setStocks(request.stocks());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stocks")
    public GetStocksResponse getStocks() {
        return new GetStocksResponse(bankService.getStocks());
    }
}