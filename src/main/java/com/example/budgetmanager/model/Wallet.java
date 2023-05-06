package com.example.budgetmanager.model;

import com.example.budgetmanager.dto.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double operationAmount;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private double balance;
    private String category;
    @CreationTimestamp
    private Date date;
}
