package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.Category;
import com.budget.tp_budget.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // Méthode pour sauvegarder une catégorie dans la base de données.
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Méthode pour récupérer toutes les catégories depuis la base de données.
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Méthode pour récupérer une catégorie par son ID depuis la base de données.
    // Si l'ID n'existe pas, la méthode renvoie null.
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
