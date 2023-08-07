package com.budget.tp_budget.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String description;

  @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
