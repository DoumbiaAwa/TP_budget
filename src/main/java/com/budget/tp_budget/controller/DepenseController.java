package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.*;
import com.budget.tp_budget.repository.BudgetRepository;
import com.budget.tp_budget.repository.UserRepository;
import com.budget.tp_budget.service.CategoryService;
import com.budget.tp_budget.service.DepenseService;
import com.budget.tp_budget.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/depense")
public class DepenseController {
    @Autowired
    private DepenseService depenseService;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public Depense creer(@RequestBody Depense depense) {
        double montant = depense.getMontant();
        Budget budget = depense.getBudget();
        depense.setBudget(budget);
        double montantCourant = budget.getMontantCourant();
        double reste = montantCourant - montant;
      if (montant <= montantCourant) {
          budget.setMontantCourant((int) reste);
          depenseService.creerDepense(depense);
          budgetRepository.save(budget);
          if (reste <= (0.1 * budget.getAmount())) {
              Notification notification = new Notification();
              notification.setType("alert");
              notification.setMessage("Attention budget presque atteint!" + budget.getCategory().getName() + " Montant restant : " + budget.getMontantCourant());
              notification.setDate(String.valueOf(LocalDate.now()));
              notification.setDepense(depense);
              notificationService.saveNotification(notification);
              notificationService.sendEmail(
                  budget.getUser().getEmail(),
                  "Budget faible",
                  " " + notification.getMessage() + " " + notification.getDate());
          }
         // return depenseService.creerDepense(depense);
          throw new RuntimeException("Depense faite avec success");
      }
        throw new RuntimeException("Depense trop élevée pour le budget");
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
        Depense depense = depenseService.getDepenseById(id);
        Budget budget = depense.getBudget();
        double montantCourant = budget.getMontantCourant();
        double montant = depense.getMontant();
        double reste = montantCourant + montant;
        budget.setMontantCourant((int) reste);
        budgetRepository.save(budget);
        depenseService.deleteDepense(id);
    }

    @PutMapping("/update/{depense_id}")
    public Depense updateDepense(@PathVariable("depense_id") Long id, @RequestBody Depense depense) {
        Depense depense1 = depenseService.getDepenseById(id);
        //depense1.setTitre(depense.getTitre());
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
