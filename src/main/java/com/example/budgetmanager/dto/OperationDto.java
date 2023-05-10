package com.example.budgetmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class OperationDto {
    private double value;
    private OperationType operationType;
    private String category;
    private double balance;
    private Date date;
}
