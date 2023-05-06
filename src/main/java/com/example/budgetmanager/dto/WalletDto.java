package com.example.budgetmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class WalletDto {
    private double operationAmount;
    private OperationType operationType;
    private String category;
}
