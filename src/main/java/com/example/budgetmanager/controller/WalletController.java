package com.example.budgetmanager.controller;

import com.example.budgetmanager.dto.IncomeSource;
import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OutcomeCategory;
import com.example.budgetmanager.dto.forms.HistoryPeriodOption;
import com.example.budgetmanager.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    @GetMapping("")
    String wallet(@RequestParam(required = false, name = "period") HistoryPeriodOption periodOption, Model model){
        List<OperationDto> operations;

        if (periodOption == null){
            operations = walletService.getOperationsFromDate(HistoryPeriodOption.ALL);
        } else {
            operations = walletService.getOperationsFromDate(periodOption);
        }

        model.addAttribute("operations", operations);
        model.addAttribute("periodOptions", HistoryPeriodOption.values());
        model.addAttribute("incomeSources", IncomeSource.values());
        model.addAttribute("outcomeCategories", OutcomeCategory.values());
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
