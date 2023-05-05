package com.example.budgetmanager.repo;

import com.example.budgetmanager.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
