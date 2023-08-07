package com.budget.tp_budget.service;

import com.budget.tp_budget.repository.RapportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {
    @Autowired
    private RapportRepository rapportRepository;

    // Méthode pour récupérer tous les rapports
    public List<Rapport> getAllRapports() {
        return rapportRepository.findAll();
    }

    // Méthode pour récupérer un rapport par son identifiant
    public Rapport getRapportById(Long rapportId) {
        return rapportRepository.findById(rapportId).orElse(null);
    }

    // Méthode pour créer un nouveau rapport
    public Rapport createRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }


    // Méthode pour supprimer un rapport
    public void deleteRapport(Long rapportId) {
        rapportRepository.deleteById(rapportId);
    }


}
