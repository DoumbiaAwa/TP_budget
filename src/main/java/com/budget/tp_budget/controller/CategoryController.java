package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Category;
import com.budget.tp_budget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Endpoint pour créer une catégorie en utilisant la méthode HTTP POST.
    // Le corps de la requête doit contenir un objet JSON avec le nom de la catégorie.
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    // Endpoint pour récupérer toutes les catégories en utilisant la méthode HTTP GET.
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Endpoint pour récupérer une catégorie par son ID en utilisant la méthode HTTP GET.
    // L'ID de la catégorie est spécifié dans l'URL.
    // Si l'ID n'existe pas, le endpoint renverra une réponse avec un statut 404 (Not Found).
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
