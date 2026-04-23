package org.example.banksimulation.services;

import org.example.banksimulation.dto.StockDto;
import org.example.banksimulation.reposiories.BankRepository;
import org.example.banksimulation.services.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.example.banksimulation.TestcontainersConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class BankServiceTest {

    @Autowired
    private BankService bankService;

    @Test
    void shouldSetBankState() {
        // given
        var stocks = List.of(
                new StockDto("stock1", 99),
                new StockDto("stock2", 1)
        );

        // when
        bankService.setStocks(stocks);

        // then
        var result = bankService.getStocks();
        assertThat(result).hasSize(2);
        assertThat(result).extracting("name").containsExactly("stock1", "stock2");
        assertThat(result).extracting("quantity").containsExactly(99, 1);
    }
}