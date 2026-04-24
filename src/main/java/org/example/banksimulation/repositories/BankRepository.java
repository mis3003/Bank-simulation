package org.example.banksimulation.repositories;

import jakarta.persistence.LockModeType;
import org.example.banksimulation.entities.BankStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankRepository extends JpaRepository<BankStock, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BankStock b WHERE b.name = :name")
    Optional<BankStock> findByNameWithLock(@Param("name") String name);
}
