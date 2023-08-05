package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Budget;
import com.budget.tp_budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    //Endpoint pour ajouter un budget
    @PostMapping("/add")
    public String add(@RequestBody Budget budget){
        budgetService.add(budget);
        return "add successful";
    }

    //Endpoint pour recuperer la liste des budet
    @GetMapping("/list")
    public List<Budget> read(){
        return budgetService.read();
    }

    //Endpoint pour supprimer un budget
    @DeleteMapping("/supprimer/{id}")
    public String delete(@PathVariable int id){
        budgetService.delete(id);
        return "delete successful";
    }

    //Endpoint pour Modifier un budget specifique
    @PutMapping("/modifier")
    public Budget update(@RequestBody Budget budget){
        return budgetService.update(budget);
    }
}
