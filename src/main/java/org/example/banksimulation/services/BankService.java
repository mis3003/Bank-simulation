package org.example.banksimulation.services;

import org.example.banksimulation.dto.StockDto;
import org.example.banksimulation.entieties.BankStock;
import org.example.banksimulation.reposiories.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void setStocks(List<StockDto> stocks) {
        bankRepository.deleteAll();
        var entities = stocks.stream()
                .map(dto -> new BankStock(dto.name(), dto.quantity()))
                .toList();
        bankRepository.saveAll(entities);
    }

    public List<StockDto> getStocks() {
        return bankRepository.findAll().stream()
                .map(stock -> new StockDto(stock.getName(), stock.getQuantity()))
                .toList();
    }
}