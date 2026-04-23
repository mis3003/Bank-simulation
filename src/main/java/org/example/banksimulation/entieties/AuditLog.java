package org.example.banksimulation.entieties;

import jakarta.persistence.*;


@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // "buy" lub "sell"

    @Column(nullable = false)
    private String walletId;

    @Column(nullable = false)
    private String stockName;

    protected AuditLog() {}

    public AuditLog(String type, String walletId, String stockName) {
        this.type = type;
        this.walletId = walletId;
        this.stockName = stockName;
    }

    public String getType() { return type; }
    public String getWalletId() { return walletId; }
    public String getStockName() { return stockName; }
}