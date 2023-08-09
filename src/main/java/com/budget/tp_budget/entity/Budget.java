package com.budget.tp_budget.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate starDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Max(value = 500000,message = "le montant ne doit pas depassé 500 000 FCFA")
    @Min(value = 5000, message = "le montant ne pas etre inferieur a 5000 FCFA")
    private int amount;

    @Column(nullable = false)
    private int montantCourant;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Depense> depenses;
}
