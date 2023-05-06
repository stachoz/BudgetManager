package com.example.budgetmanager.repo;

import com.example.budgetmanager.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findTopByOrderByIdDesc();
}
