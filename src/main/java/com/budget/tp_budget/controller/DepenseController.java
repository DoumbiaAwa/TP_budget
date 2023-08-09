package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Budget;
import com.budget.tp_budget.entity.Category;
import com.budget.tp_budget.entity.Depense;
import com.budget.tp_budget.entity.User;
import com.budget.tp_budget.repository.BudgetRepository;
import com.budget.tp_budget.repository.UserRepository;
import com.budget.tp_budget.service.CategoryService;
import com.budget.tp_budget.service.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depense")
public class DepenseController {
    @Autowired
    private DepenseService depenseService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BudgetRepository budgetRepository;

    @PostMapping("/user/{user_id}/category/{category_id}/create")
    public Depense creer(@RequestBody Depense depense, @PathVariable("user_id") Long user_id, @PathVariable("category_id") Long cat_id) {
        User user = userRepository.findById(Math.toIntExact(user_id));
        Category category = categoryService.getCategoryById(cat_id);

        if (user != null && category != null) {
            depense.setUser(user);
            depense.setCategory(category);
            Budget budget = budgetRepository.findLastBudget(category.getBudgets());
            Long montantBudget = (long) budget.getAmount();
            if (montantBudget > depense.getMontant()) {
                budget.setAmount((int) (montantBudget - depense.getMontant()));
                budgetRepository.save(budget);
                return depenseService.creerDepense(depense);
            } else {
                //notificationService.sendNotification(user, "Budget insuffisant");
            }

        }
        return null;
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

    @GetMapping("/categories/{category_id}")
    public List<Depense> totalDepenseCategory(@PathVariable("category_id") Long category_id) {
      return depenseService.getAllDepenseByCategorie(category_id);
    }
}
