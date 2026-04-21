package org.example.banksimulation.services;

import org.example.banksimulation.TestcontainersConfiguration;
import org.example.banksimulation.entieties.BankStock;
import org.example.banksimulation.reposiories.BankRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Autowired
    private BankRepository bankRepository;

    @Test
    void shouldBuyStock() {
        // given
        bankRepository.save(new BankStock("stock1", 10));

        // when
        walletService.trade("wallet1", "stock1", "buy");

        // then
        var wallet = walletService.getWallet("wallet1");
        assertThat(wallet.stocks()).hasSize(1);
        assertThat(wallet.stocks().get(0).name()).isEqualTo("stock1");
        assertThat(wallet.stocks().get(0).quantity()).isEqualTo(1);
    }

    @Test
    void shouldReduceBankStockAfterBuy() {
        // given
        bankRepository.save(new BankStock("stock1", 10));

        // when
        walletService.trade("wallet1", "stock1", "buy");

        // then
        var bankStock = bankRepository.findByName("stock1");
        assertThat(bankStock.get().getQuantity()).isEqualTo(9);
    }

    @Test
    void shouldThrowWhenBuyingNonExistentStock() {
        assertThatThrownBy(() -> walletService.trade("wallet1", "nonexistent", "buy"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404");
    }

    @Test
    void shouldThrowWhenBankHasNoStock() {
        // given
        bankRepository.save(new BankStock("stock1", 0));

        // when/then
        assertThatThrownBy(() -> walletService.trade("wallet1", "stock1", "buy"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("400");
    }
}