package com.budget.tp_budget.repository;

import com.budget.tp_budget.entity.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodeRepository extends JpaRepository<Periode, Integer>{
}
