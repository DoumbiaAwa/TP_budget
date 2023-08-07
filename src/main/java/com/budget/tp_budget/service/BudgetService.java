package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.Budget;
import com.budget.tp_budget.entity.User;
import com.budget.tp_budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    //Methode pour ajouter un budget
    public  String add(Budget budget){
        LocalDate datedebut =  budget.getStarDate();
        LocalDate datefin = datedebut.plusDays(29);
        budget.setEndDate(datefin);
        // String formattedDate = datefin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        budgetRepository.save(budget);
        return "add successful";
    }

    //Methode pour recuperer la liste des budget
    public List<Budget> read(){
        return budgetRepository.findAll();
    }

    //Methode pour supprimer un budget
    public String delete(int id){
        budgetRepository.deleteById(id);
        return "suppression successful";
    }

    //Methode pour Modifier un budget specifique
    public Budget update(Budget budget){
        budgetRepository.save(budget);
        return budgetRepository.findById(budget.getId());
    }
}
