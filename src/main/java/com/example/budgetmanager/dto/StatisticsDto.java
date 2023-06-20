package com.example.budgetmanager.dto;

import lombok.Getter;

@Getter
public class StatisticsDto {
    private final int outcomeTransactionsNum;
    private final int incomeTransactionNum;
    private final double outcomeTransactionsValue;
    private final double incomeTransactionValue;
    private final double saldo;

    public StatisticsDto(int outcomeTransactionsNum, int incomeTransactionNum, double outcomeTransactionsValue, double incomeTransactionValue) {
        this.outcomeTransactionsNum = outcomeTransactionsNum;
        this.incomeTransactionNum = incomeTransactionNum;
        this.outcomeTransactionsValue = outcomeTransactionsValue;
        this.incomeTransactionValue = incomeTransactionValue;
        this.saldo = incomeTransactionValue - outcomeTransactionsValue;
    }
}
