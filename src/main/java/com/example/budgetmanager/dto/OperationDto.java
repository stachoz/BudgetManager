package com.example.budgetmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OperationDto {
    private double value;
    private OperationType operationType;
    private String category;
}
