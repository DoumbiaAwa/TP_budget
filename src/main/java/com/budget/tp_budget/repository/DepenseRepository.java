package com.budget.tp_budget.repository;

import com.budget.tp_budget.entity.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {

    @Query (value = "SELECT * FROM depense WHERE periode = :periode", nativeQuery = true)
    List<Depense> findAllByOrderByPeriode(String periode);

    @Query (value = "SELECT * FROM depense WHERE category_id = :category_id", nativeQuery = true)
    List<Depense> findAllByOrderByCategorie(Long category_id);
}
