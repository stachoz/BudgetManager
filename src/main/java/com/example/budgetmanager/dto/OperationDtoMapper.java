package com.example.budgetmanager.dto;

import com.example.budgetmanager.model.Operation;
import com.example.budgetmanager.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationDtoMapper {
    private final WalletRepository walletRepository;
    public Operation map(OperationDto dto){
        Operation operation = new Operation();
        double operationAmount = dto.getValue();
        OperationType operationType = dto.getOperationType();
        operation.setValue(operationAmount);
        operation.setType(operationType);
        // avoid lambdas have to be final or effectively final error
        double negativeOperationAmount;
        if(operationType.equals(OperationType.OUTCOME)) {
            negativeOperationAmount = -operationAmount;
        } else {
            negativeOperationAmount = operationAmount;
        }
        walletRepository.findTopByOrderByIdDesc().ifPresentOrElse(
                lastOperationRecord -> operation.setBalance(lastOperationRecord.getBalance() + negativeOperationAmount),
                () -> operation.setBalance(negativeOperationAmount)
        );
        operation.setCategory(dto.getCategory());
        return operation;
    }
}
