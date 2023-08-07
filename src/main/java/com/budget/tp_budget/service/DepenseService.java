package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.Depense;
import com.budget.tp_budget.repository.DepenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepenseService {
    @Autowired
    private DepenseRepository depenseRepository;

    public Depense creerDepense(Depense depense) {
        return depenseRepository.save(depense);
    }

    public Depense getDepenseById(Long id) {
        return depenseRepository.findById(id).orElse(null);
    }

    public List<Depense> getAllDepense() {
        return depenseRepository.findAll();
    }

    public void deleteDepense(Long id) {
        depenseRepository.deleteById(id);
    }

    public List<Depense> getAllDepenseByPeriode(String periode) {
        return depenseRepository.findAllByOrderByPeriode(periode);
    }
}
