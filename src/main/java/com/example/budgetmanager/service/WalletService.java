package com.example.budgetmanager.service;

import com.example.budgetmanager.dto.OperationType;
import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OperationDtoMapper;
import com.example.budgetmanager.model.Operation;
import com.example.budgetmanager.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final OperationDtoMapper operationDtoMapper;

    public void addIncome(OperationDto walletDto){
        walletDto.setOperationType(OperationType.INCOME);
        Operation operation = operationDtoMapper.map(walletDto);
        walletRepository.save(operation);
    }

    public void addOutcome(OperationDto walletDto){
        walletDto.setOperationType(OperationType.OUTCOME);
        Operation operation = operationDtoMapper.map(walletDto);
        walletRepository.save(operation);
    }

    public List<OperationDto> getAllOperations(){
        List<Operation> operations = walletRepository.findAll();
        operations.stream().map(OperationDtoMapper::map).collect(Collectors.toList())

    }
}
