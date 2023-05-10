package com.example.budgetmanager.repo;

import com.example.budgetmanager.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findTopByOrderByIdDesc();
    List<Operation> findAllByOrderByIdDesc();
}
