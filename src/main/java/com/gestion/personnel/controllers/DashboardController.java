package com.gestion.personnel.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.personnel.repositories.DirectionRepository;
import com.gestion.personnel.repositories.EntrepriseRepository;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getCounts() {
        long entreprisesCount = entrepriseRepository.count();
        long directionsCount = directionRepository.count();

        Map<String, Long> counts = new HashMap<>();
        counts.put("entreprises", entreprisesCount);
        counts.put("directions", directionsCount);

        return ResponseEntity.ok(counts);
    }
}

