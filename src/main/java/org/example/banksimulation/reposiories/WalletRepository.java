package org.example.banksimulation.reposiories;

import org.example.banksimulation.entieties.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository  extends JpaRepository<Wallet, Long> {
}
