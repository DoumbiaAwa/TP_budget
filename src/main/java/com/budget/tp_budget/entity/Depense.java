package com.budget.tp_budget.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String titre;

    @Column (nullable = false)
    private Date date;

    @Column (nullable = false)
    private Double montant;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private String periode;

}
