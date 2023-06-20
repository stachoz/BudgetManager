package com.example.budgetmanager.repo;

import com.example.budgetmanager.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findTopByOrderByIdDesc();

    @Query(value = "select * from operation order by id desc",nativeQuery = true)
    List<Operation> getAllOperationsDesc();
    @Query(value ="select * from operation where MONTH(date) = MONTH(now()) and YEAR(date) = YEAR(now())", nativeQuery = true)
    List<Operation> getCurrentMonthOperations();
    @Query(value ="select * from operation where date > :dateTime", nativeQuery = true)
    List<Operation> getOperationFromDate(LocalDate dateTime);

}
