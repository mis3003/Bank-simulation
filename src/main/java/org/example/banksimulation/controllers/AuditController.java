package org.example.banksimulation.controllers;

import org.example.banksimulation.dto.AuditLogDto;
import org.example.banksimulation.dto.AuditLogResponse;
import org.example.banksimulation.repositories.AuditLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    public AuditController(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @GetMapping("/log")
    public AuditLogResponse getLog() {
        var logs = auditLogRepository.findAll().stream()
                .map(log -> new AuditLogDto(log.getType(), log.getWalletId(), log.getStockName()))
                .toList();
        return new AuditLogResponse(logs);
    }
}