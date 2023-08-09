package com.budget.tp_budget.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notification")
public class Notification {
  @Id
  @GeneratedValue(strategy =GenerationType.IDENTITY )
  private long id_N;

  @Column(length = 150)
  private String message;

  @Column(length = 150)
  private String date;

  @Column(length = 150)
  private String type;

  @OneToOne
  private Depense depense;
}