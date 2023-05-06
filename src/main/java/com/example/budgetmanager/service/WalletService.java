package com.example.budgetmanager.service;

import com.example.budgetmanager.dto.OperationType;
import com.example.budgetmanager.dto.WalletDto;
import com.example.budgetmanager.dto.WalletDtoMapper;
import com.example.budgetmanager.model.Wallet;
import com.example.budgetmanager.repo.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletDtoMapper walletDtoMapper;

    @Transactional
    public void addIncome(WalletDto walletDto){
        walletDto.setOperationType(OperationType.INCOME);
        Wallet wallet = walletDtoMapper.map(walletDto);
        walletRepository.save(wallet);
    }

    @Transactional
    public void addOutcome(WalletDto walletDto){
        walletDto.setOperationType(OperationType.OUTCOME);
        Wallet wallet = walletDtoMapper.map(walletDto);
        walletRepository.save(wallet);
    }
}
