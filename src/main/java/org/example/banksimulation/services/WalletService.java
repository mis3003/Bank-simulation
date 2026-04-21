package org.example.banksimulation.services;

import org.example.banksimulation.dto.WalletDto;
import org.example.banksimulation.reposiories.AuditLogRepository;
import org.example.banksimulation.reposiories.BankRepository;
import org.example.banksimulation.reposiories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final BankRepository bankRepository;
    private final AuditLogRepository auditLogRepository;

    public WalletService(WalletRepository walletRepository,
                         BankRepository bankRepository,
                         AuditLogRepository auditLogRepository) {
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.auditLogRepository = auditLogRepository;
    }

    public void trade(String walletId, String stockName, String type) {
        // buy lub sell
    }

    public WalletDto getWallet(String walletId) {
        return null;
    }

    public int getStockQuantity(String walletId, String stockName) {
        return 0;
    }
}