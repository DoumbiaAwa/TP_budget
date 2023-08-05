package com.budget.tp_budget.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email(message = "address email invalide")
    @Column(unique = true)
    private  String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "votre message est incorrecte")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "categoriesCreer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("User")
    private List<Category> categoriesCreer;
}
