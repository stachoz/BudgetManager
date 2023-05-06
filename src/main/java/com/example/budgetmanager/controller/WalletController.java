package com.example.budgetmanager.controller;

import com.example.budgetmanager.dto.OperationDto;
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
        model.addAttribute("wallet", new OperationDto());
        return "wallet";
    }

    @PostMapping("/addIncome")
    String addIncome(){
        OperationDto walletDto = new OperationDto();
        walletService.addIncome(walletDto);
        return "redirect:";
    }

    @PostMapping("/addOutcome")
    String addOutcome(){
        OperationDto walletDto = new OperationDto();
        walletService.addOutcome(walletDto);
        return "redirect:";
    }


}
