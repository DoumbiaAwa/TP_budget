package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.Rapport;
import com.budget.tp_budget.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapports")
public class RapportController {
    @Autowired
    private RapportService rapportService;

    // Endpoint pour récupérer la liste de tous les rapports
    @GetMapping
    public List<Rapport> getAllRapports() {
        return rapportService.getAllRapports();
    }

    // Endpoint pour récupérer un rapport par son identifiant
    @GetMapping("/{rapportId}")
    public ResponseEntity<Rapport> getRapportById(@PathVariable Long rapportId) {
        Rapport rapport = rapportService.getRapportById(rapportId);
        if (rapport != null) {
            return ResponseEntity.ok(rapport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour créer un nouveau rapport
    @PostMapping("")
    public ResponseEntity<Rapport> createRapport(@RequestBody Rapport rapport) {
        Rapport createdRapport = rapportService.createRapport(rapport);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRapport);
    }

    // Endpoint pour supprimer un rapport
    @DeleteMapping("/{rapportId}")
    public ResponseEntity<Void> deleteRapport(@PathVariable Long rapportId) {
        rapportService.deleteRapport(rapportId);
        return ResponseEntity.noContent().build();
    }
}
