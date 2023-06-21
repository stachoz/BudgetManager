package com.example.budgetmanager.controller;

import com.example.budgetmanager.dto.IncomeSource;
import com.example.budgetmanager.dto.OperationDto;
import com.example.budgetmanager.dto.OutcomeCategory;
import com.example.budgetmanager.dto.StatisticsDto;
import com.example.budgetmanager.dto.forms.HistoryPeriodOption;
import com.example.budgetmanager.service.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;
    @GetMapping("/")
    String wallet(@RequestParam(required = false, name = "period") HistoryPeriodOption periodOption, Model model, HttpSession session){
        List<OperationDto> operations;
        StatisticsDto walletStats;
        if (periodOption == null){
            operations = walletService.getOperationsFromDate(HistoryPeriodOption.ALL);
            walletStats = walletService.getWalletStatistics(HistoryPeriodOption.ALL);
        } else {
            operations = walletService.getOperationsFromDate(periodOption);
            walletStats = walletService.getWalletStatistics(periodOption);
        }

        model.addAttribute("operations", operations);
        model.addAttribute("periodOptions", HistoryPeriodOption.values());
        model.addAttribute("incomeSources", IncomeSource.values());
        model.addAttribute("outcomeCategories", OutcomeCategory.values());
        model.addAttribute("operation", new OperationDto());
        model.addAttribute("stats", walletStats);
        session.setAttribute("periodOption", periodOption);
        return "wallet";
    }

    @PostMapping("/addIncome")
    String addIncome(HttpSession session, OperationDto dto){
        walletService.addIncome(dto);
        HistoryPeriodOption periodOption = (HistoryPeriodOption) session.getAttribute("periodOption");
        return "redirect:?period=" + String.valueOf(periodOption);
    }

    @PostMapping("/addOutcome")
    String addOutcome(HttpSession session, OperationDto dto){
        HistoryPeriodOption periodOption = (HistoryPeriodOption) session.getAttribute("periodOption");
        walletService.addOutcome(dto);
        return "redirect:?period=" + String.valueOf(periodOption);
    }

}
