package com.example.budgetmanager.repo;

import com.example.budgetmanager.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Optional<Operation> findTopByOrderByIdDesc();
    @Query(value = "select * from operation order by id desc",nativeQuery = true)
    List<Operation> getAllOperationsDesc();
    @Query(value ="select * from operation where date > :dateTime order by id desc", nativeQuery = true)
    List<Operation> getOperationFromDate(LocalDate dateTime);

    @Query(value = "select count(id) from operation where date > :dateTime and type = :type",nativeQuery = true)
    int countOperations(LocalDate dateTime, String type);
    @Query(value = "select ifnull(sum(value), 0) from operation where date > :dateTime and type = :type", nativeQuery = true)
    double operationValue(LocalDate dateTime, String type);

}
