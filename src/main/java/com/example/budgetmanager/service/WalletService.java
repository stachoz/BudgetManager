package com.example.budgetmanager.service;

import com.example.budgetmanager.dto.OperationType;
import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OperationDtoMapper;
import com.example.budgetmanager.model.Operation;
import com.example.budgetmanager.repo.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final OperationRepository operationRepository;
    private final OperationDtoMapper operationDtoMapper;

    public void addIncome(OperationDto walletDto){
        walletDto.setOperationType(OperationType.INCOME);
        Operation operation = operationDtoMapper.map(walletDto);
        operationRepository.save(operation);
    }

    public void addOutcome(OperationDto walletDto){
        walletDto.setOperationType(OperationType.OUTCOME);
        Operation operation = operationDtoMapper.map(walletDto);
        operationRepository.save(operation);
    }

    public List<OperationDto> getAllOperations(){
        List<OperationDto> dtos = new ArrayList<>();
        operationRepository.getAllOperationsDesc().forEach(o -> dtos.add(operationDtoMapper.map(o)));
        return dtos;
    }

}
