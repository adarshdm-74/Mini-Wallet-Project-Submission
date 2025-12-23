package com.example.mini_wallet.controller;

import com.example.mini_wallet.Repository.UserWalletRepository;
import com.example.mini_wallet.dto.CreateUserRequest;
import com.example.mini_wallet.dto.TransferRequest;
import com.example.mini_wallet.model.UserWallet;
import com.example.mini_wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class WalletController {

    private final UserWalletRepository repository;
    private final WalletService service;

    public WalletController(UserWalletRepository repository, WalletService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/users")
    public UserWallet createUser(@RequestBody CreateUserRequest request) {
        UserWallet user = new UserWallet();
        user.setName(request.name);
        user.setBalance(request.balance);
        return repository.save(user);
    }

    @GetMapping("/users/{id}/balance")
    public String getBalance(@PathVariable UUID id) {
        UserWallet user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getBalance().toString();
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        service.transferMoney(request);
        return "Transfer Successful";
    }
}