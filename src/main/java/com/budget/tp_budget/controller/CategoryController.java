package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Category;
import com.budget.tp_budget.entity.User;
import com.budget.tp_budget.service.CategoryService;
import com.budget.tp_budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    // Récupère toutes les catégories d'un utilisateur
    @GetMapping
    public List<Category> getAllCategoriesForUser(@PathVariable Long userId) {
        return categoryService.getAllCategoriesForUser(userId);
    }

    // Crée une nouvelle catégorie
    @PostMapping
    public ResponseEntity<Category> createCategory(@PathVariable Long userId, @RequestBody Category category) {
        // Assurez-vous que l'utilisateur existe
        User user = userService.getUserById(userId);

        category.setUser(user);
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }


    // Récupère une catégorie par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long userId, @PathVariable Long id) {
        // Assurez-vous que l'utilisateur existe (vérification non montrée dans cet exemple)
        User user = userService.getUserById(userId);

        Category category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(category);
    }

    // Supprime une catégorie par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long userId, @PathVariable Long id) {
        // Assurez-vous que l'utilisateur existe
        User user = userService.getUserById(userId);
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
