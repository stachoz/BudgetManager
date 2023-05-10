package com.example.budgetmanager.dto;

import com.example.budgetmanager.model.Operation;
import com.example.budgetmanager.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationDtoMapper {
    private final WalletRepository walletRepository;
    public Operation map(OperationDto dto){
        Operation operation = new Operation();
        double value = dto.getValue();
        OperationType operationType = dto.getOperationType();
        operation.setValue(value);
        operation.setType(operationType);
        operation.setBalance(countBalance(value, operationType));
        operation.setCategory(dto.getCategory());
        return operation;
    }

    public OperationDto map(Operation operation){
        OperationDto dto = new OperationDto();
        dto.setOperationType(operation.getType());
        dto.setCategory(operation.getCategory());
        dto.setValue(operation.getValue());
        dto.setBalance(operation.getBalance());
        dto.setDate(operation.getDate());
        return dto;
    }

    private double countBalance(double value, OperationType t){
        double lastBalance = 0;
        double updatedBalance = 0;
        Optional<Operation> lastOperation = walletRepository.findTopByOrderByIdDesc();
        if(lastOperation.isPresent()){
            lastBalance = lastOperation.get().getBalance();
        }
        if(t.equals(OperationType.INCOME)){
            updatedBalance = lastBalance + value;
        } else if(t.equals(OperationType.OUTCOME)){
            updatedBalance = lastBalance - value;
        }
        return updatedBalance;
    }

}
