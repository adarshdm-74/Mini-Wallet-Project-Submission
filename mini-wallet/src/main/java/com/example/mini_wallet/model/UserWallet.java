package com.example.mini_wallet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "user_wallet")
public class UserWallet {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}