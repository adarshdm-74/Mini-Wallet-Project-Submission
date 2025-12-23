package com.example.mini_wallet.service;

import com.example.mini_wallet.Repository.UserWalletRepository;
import com.example.mini_wallet.dto.TransferRequest;
import com.example.mini_wallet.model.UserWallet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final UserWalletRepository repository;

    public WalletService(UserWalletRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void transferMoney(TransferRequest request) {

        if (request.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        UserWallet sender = repository.findById(request.fromUserId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        UserWallet receiver = repository.findById(request.toUserId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getBalance().compareTo(request.amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance().subtract(request.amount));
        receiver.setBalance(receiver.getBalance().add(request.amount));

        repository.save(sender);
        repository.save(receiver);
    }
}