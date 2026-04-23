package org.example.banksimulation.dto;

import java.util.List;

public record AuditLogResponse(List<AuditLogDto> log) {}
