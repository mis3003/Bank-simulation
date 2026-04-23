package org.example.banksimulation.reposiories;

import org.example.banksimulation.entieties.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
