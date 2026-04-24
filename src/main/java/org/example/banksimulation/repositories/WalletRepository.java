package org.example.banksimulation.repositories;

import org.example.banksimulation.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository  extends JpaRepository<Wallet, String> {
}
