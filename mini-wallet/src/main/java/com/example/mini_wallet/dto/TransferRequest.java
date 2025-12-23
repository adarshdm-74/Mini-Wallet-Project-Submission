package com.example.mini_wallet.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferRequest {
    public UUID fromUserId;
    public UUID toUserId;
    public BigDecimal amount;
}
