package org.example.banksimulation.controllers;

import org.example.banksimulation.dto.TradeRequest;
import org.example.banksimulation.dto.WalletDto;
import org.example.banksimulation.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets/{wallet_id}/stocks/{stock_name}")
    public ResponseEntity<Void> trade(
            @PathVariable("wallet_id") String walletId,
            @PathVariable("stock_name") String stockName,
            @RequestBody TradeRequest request) {
        walletService.trade(walletId, stockName, request.type());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallets/{wallet_id}")
    public WalletDto getWallet(@PathVariable("wallet_id") String walletId) {
        return walletService.getWallet(walletId);
    }

    @GetMapping("/wallets/{wallet_id}/stocks/{stock_name}")
    public int getStockQuantity(
            @PathVariable("wallet_id") String walletId,
            @PathVariable("stock_name") String stockName) {
        return walletService.getStockQuantity(walletId, stockName);
    }
}