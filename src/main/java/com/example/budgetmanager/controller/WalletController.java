package com.example.budgetmanager.controller;

import com.example.budgetmanager.dto.IncomeSource;
import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OutcomeCategory;
import com.example.budgetmanager.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    @GetMapping("/")
    String wallet(Model model){
        IncomeSource[] incomeSources = IncomeSource.values();
        OutcomeCategory[] outcomeCategories = OutcomeCategory.values();
        model.addAttribute("incomeSources", incomeSources);
        model.addAttribute("outcomeCategories", outcomeCategories);
        model.addAttribute("operation", new OperationDto());
        return "wallet";
    }

    @PostMapping("/addIncome")
    String addIncome(){
        OperationDto operationDto = new OperationDto();
        walletService.addIncome(operationDto);
        return "redirect:";
    }

    @PostMapping("/addOutcome")
    String addOutcome(){
        OperationDto operationDto = new OperationDto();
        walletService.addOutcome(operationDto);
        return "redirect:";
    }


}
