package com.budget.tp_budget.repository;

import com.budget.tp_budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Budget findById(int id);

    @Query(value = "select * from budget order by id desc limit 1", nativeQuery = true)
    Budget findLastBudget(List<Budget> listBudget);
}
