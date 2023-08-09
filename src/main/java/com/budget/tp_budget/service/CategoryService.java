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

    // Crée une nouvelle catégorie
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Récupère toutes les catégories d'un utilisateur
    public List<Category> getAllCategoriesForUser(Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    // Récupère une catégorie par son ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    // Supprime une catégorie par son ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
