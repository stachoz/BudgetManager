package com.example.budgetmanager.service;

import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OperationDtoMapper;
import com.example.budgetmanager.dto.OperationType;
import com.example.budgetmanager.dto.forms.HistoryPeriodOption;
import com.example.budgetmanager.model.Operation;
import com.example.budgetmanager.repo.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        return operationRepository.getAllOperationsDesc().stream().map(operationDtoMapper::map)
                .collect(Collectors.toList());
    }
    public List<OperationDto> getOperationsFromDate(HistoryPeriodOption periodOption) {
        LocalDate now = LocalDate.now();
        LocalDate filterDate = null;
        switch (periodOption){
            case ALL -> {
                return getAllOperations();
            }
            case TODAY -> filterDate = now;
            case THIS_MONTH -> {
                int dayOfMonth = now.getDayOfMonth() - 1;
                filterDate = now.minusDays(dayOfMonth);
            }
            case THIS_WEEK -> {
                int dayOfWeek = now.getDayOfWeek().getValue() - 1;
                filterDate = now.minusDays(dayOfWeek);
            }
            case LAST_MONTH ->  {
                int dayOfMonth = now.getDayOfMonth() - 1;
                filterDate = now.minusDays(dayOfMonth).minusMonths(1);
            }
        }
        return operationRepository.getOperationFromDate(filterDate).stream()
                .map(operationDtoMapper::map)
                .collect(Collectors.toList());
    }
}
