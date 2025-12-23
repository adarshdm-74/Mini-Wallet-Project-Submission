package com.example.mini_wallet.Repository;

import com.example.mini_wallet.model.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserWalletRepository extends JpaRepository<UserWallet, UUID> {
}