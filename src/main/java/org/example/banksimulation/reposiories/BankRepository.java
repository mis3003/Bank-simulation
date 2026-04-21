package org.example.banksimulation.reposiories;

import org.example.banksimulation.entieties.BankStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<BankStock, Long> {
    Optional<BankStock> findByName(String stock1);
}
