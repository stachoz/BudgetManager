package com.example.budgetmanager.controller;

import com.example.budgetmanager.dto.IncomeSource;
import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OutcomeCategory;
import com.example.budgetmanager.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    @GetMapping("/")
    String wallet(Model model){
        IncomeSource[] incomeSources = IncomeSource.values();
        OutcomeCategory[] outcomeCategories = OutcomeCategory.values();
        List<OperationDto> allOperations = walletService.getAllOperations();
        model.addAttribute("operations", allOperations);
        model.addAttribute("incomeSources", incomeSources);
        model.addAttribute("outcomeCategories", outcomeCategories);
        model.addAttribute("operation", new OperationDto());
        return "wallet";
    }

    @PostMapping("/addIncome")
    String addIncome(OperationDto dto){
        walletService.addIncome(dto);
        return "redirect:";
    }

    @PostMapping("/addOutcome")
    String addOutcome(OperationDto dto){
        walletService.addOutcome(dto);
        return "redirect:";
    }


}
