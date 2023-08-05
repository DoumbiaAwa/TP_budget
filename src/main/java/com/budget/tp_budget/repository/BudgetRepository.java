package com.budget.tp_budget.repository;

import com.budget.tp_budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Budget findById(int id);
}
