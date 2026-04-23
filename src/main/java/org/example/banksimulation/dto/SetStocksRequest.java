package org.example.banksimulation.dto;

import java.util.List;

public record SetStocksRequest(List<StockDto> stocks) {}