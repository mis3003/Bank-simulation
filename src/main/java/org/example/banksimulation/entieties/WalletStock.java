package org.example.banksimulation.entieties;

import jakarta.persistence.*;


@Entity
@Table(name = "wallet_stock")
public class WalletStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private int quantity;

    protected WalletStock() {}

    public WalletStock(Wallet wallet, String stockName, int quantity) {
        this.wallet = wallet;
        this.stockName = stockName;
        this.quantity = quantity;
    }

    public String getStockName() { return stockName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}