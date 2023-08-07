package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Depense;
import com.budget.tp_budget.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depense")
public class DepenseController {
    @Autowired
    private DepenseService depenseService;

    @PostMapping("/creer")
    public Depense creer(@RequestBody Depense depense) {
        return depenseService.creerDepense(depense);
    }

    @GetMapping("/{depense_id}")
    public Depense getDepense(@PathVariable("depense_id") Long id) {
        return depenseService.getDepenseById(id);
    }

    @GetMapping("/all")
    public List<Depense> listDepense() {
        return depenseService.getAllDepense();
    }

    @DeleteMapping("/delete/{depense_id}")
    public void deleteDepense(@PathVariable("depense_id") Long id) {
        depenseService.deleteDepense(id);
    }

    @PutMapping("/update/{depense_id}")
    public Depense updateDepense(@PathVariable("depense_id") Long id, @RequestBody Depense depense) {
        Depense depense1 = depenseService.getDepenseById(id);
        depense1.setTitre(depense.getTitre());
        depense1.setMontant(depense.getMontant());
        depense1.setDate(depense.getDate());
        depense1.setDescription(depense.getDescription());
        return depenseService.creerDepense(depense1);
    }

    @GetMapping("/total")
    public Double totalDepense() {
        List<Depense> depenses = depenseService.getAllDepense();
        Double total = 0.0;
        for (Depense depense : depenses) {
            total += depense.getMontant();
        }
        return total;
    }

    @GetMapping("/periode/{periode}")
    public List<Object> totalDepenseMois(@PathVariable("periode") String periode) {
        List<Depense> depenses = depenseService.getAllDepenseByPeriode(periode);
        Double total = 0.0;
        for (Depense depense : depenses) {
                total += depense.getMontant();
        }
        return List.of(total, depenses);
    }
}
