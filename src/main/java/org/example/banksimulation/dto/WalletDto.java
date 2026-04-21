package org.example.banksimulation.dto;

import java.util.List;

public record  WalletDto(String id, List<StockDto> stocks) {
}
