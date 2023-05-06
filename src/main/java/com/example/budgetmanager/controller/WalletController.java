package com.example.budgetmanager.controller;

import com.example.budgetmanager.dto.OperationType;
import com.example.budgetmanager.dto.WalletDto;
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
        model.addAttribute("wallet", new WalletDto());
        return "wallet";
    }

    @PostMapping("/addIncome")
    String addIncome(){
        WalletDto walletDto = new WalletDto();
        walletService.addIncome(walletDto);
        return "redirect:";
    }

    @PostMapping("/addOutcome")
    String addOutcome(){
        WalletDto walletDto = new WalletDto();
        walletService.addOutcome(walletDto);
        return "redirect:";
    }


}
