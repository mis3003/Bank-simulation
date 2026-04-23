package org.example.banksimulation.dto;

import java.util.List;

public record GetStocksResponse(List<StockDto> stocks) {}