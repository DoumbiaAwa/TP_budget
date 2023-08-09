package com.budget.tp_budget.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "periode")
public class Periode {
  @Id
  @GeneratedValue(strategy =GenerationType.IDENTITY )
  private long id;

  @Column(length = 120)
  private String type;
  @OneToMany(mappedBy = "periode", cascade = CascadeType.ALL)
  private List<Depense> depense;
}

