package org.example.banksimulation.entieties;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet {



        @Id
        private String id;
        @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<WalletStock> stocks = new ArrayList<>();

        protected Wallet() {}

        public Wallet(String id) {
            this.id = id;
        }

        public String getId() { return id; }
        public List<WalletStock> getStocks() { return stocks; }

}
