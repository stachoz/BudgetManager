package com.example.budgetmanager.dto;

import com.example.budgetmanager.model.Wallet;
import com.example.budgetmanager.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletDtoMapper {
    private final WalletRepository walletRepository;
    public Wallet map(WalletDto dto){
        Wallet wallet = new Wallet();
        double operationAmount = dto.getOperationAmount();
        OperationType operationType = dto.getOperationType();
        wallet.setOperationAmount(operationAmount);
        wallet.setOperationType(operationType);
        // avoid lambdas have to be final or effectively final error
        double negativeOperationAmount;
        if(operationType.equals(OperationType.OUTCOME)) {
            negativeOperationAmount = -operationAmount;
        } else {
            negativeOperationAmount = operationAmount;
        }
        walletRepository.findTopByOrderByIdDesc().ifPresentOrElse(
                lastWalletRecord -> wallet.setBalance(lastWalletRecord.getBalance() + negativeOperationAmount),
                () -> wallet.setBalance(negativeOperationAmount)
        );
        wallet.setCategory(dto.getCategory());
        return wallet;
    }
}
