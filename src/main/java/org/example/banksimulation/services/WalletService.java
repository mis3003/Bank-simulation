package org.example.banksimulation.services;

import org.example.banksimulation.dto.StockDto;
import org.example.banksimulation.dto.WalletDto;
import org.example.banksimulation.entieties.AuditLog;
import org.example.banksimulation.entieties.Wallet;
import org.example.banksimulation.entieties.WalletStock;
import org.example.banksimulation.reposiories.AuditLogRepository;
import org.example.banksimulation.reposiories.BankRepository;
import org.example.banksimulation.reposiories.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
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
        var bankStock = bankRepository.findByName(stockName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));

        var wallet = walletRepository.findById(walletId)
                .orElseGet(() -> walletRepository.save(new Wallet(walletId)));

        if ("buy".equals(type)) {
            if (bankStock.getQuantity() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stock in bank");
            }
            bankStock.setQuantity(bankStock.getQuantity() - 1);
            bankRepository.save(bankStock);

            var walletStock = wallet.getStocks().stream()
                    .filter(s -> s.getStockName().equals(stockName))
                    .findFirst()
                    .orElseGet(() -> {
                        var newStock = new WalletStock(wallet, stockName, 0);
                        wallet.getStocks().add(newStock);
                        return newStock;
                    });
            walletStock.setQuantity(walletStock.getQuantity() + 1);

        } else if ("sell".equals(type)) {
            var walletStock = wallet.getStocks().stream()
                    .filter(s -> s.getStockName().equals(stockName))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stock in wallet"));

            if (walletStock.getQuantity() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stock in wallet");
            }
            walletStock.setQuantity(walletStock.getQuantity() - 1);
            bankStock.setQuantity(bankStock.getQuantity() + 1);
            bankRepository.save(bankStock);
        }

        auditLogRepository.save(new AuditLog(type, walletId, stockName));
    }

    public WalletDto getWallet(String walletId) {
        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));

        var stocks = wallet.getStocks().stream()
                .map(s -> new StockDto(s.getStockName(), s.getQuantity()))
                .toList();

        return new WalletDto(wallet.getId(), stocks);
    }

    public int getStockQuantity(String walletId, String stockName) {
        var wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found"));

        return wallet.getStocks().stream()
                .filter(s -> s.getStockName().equals(stockName))
                .mapToInt(WalletStock::getQuantity)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));
    }
}