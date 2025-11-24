package com.alglab.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/algorithms")
public class AlgorithmController {
    @Autowired
    private AlgorithmService algorithmService;

    @GetMapping
    public ResponseEntity<List<Algorithm>> getAllAlgorithms() {
        List<Algorithm> algorithms = algorithmService.getAllAlgorithms();
        return ResponseEntity.ok(algorithms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Algorithm> getAlgorithmById(@PathVariable Long id) {
        Optional<Algorithm> algorithm = algorithmService.getAlgorithmById(id);
        return algorithm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Algorithm> getAlgorithmByName(@PathVariable String name) {
        Optional<Algorithm> algorithm = algorithmService.getAlgorithmByName(name);
        return algorithm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/abbreviation/{abbreviation}")
    public ResponseEntity<Algorithm> getAlgorithmByAbbreviation(@PathVariable String abbreviation) {
        Optional<Algorithm> algorithm = algorithmService.getAlgorithmByAbbreviation(abbreviation);
        return algorithm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Algorithm>> getAlgorithmsByCategory(@PathVariable String category) {
        List<Algorithm> algorithms = algorithmService.getAlgorithmsByCategory(category);
        return ResponseEntity.ok(algorithms);
    }

    @GetMapping("/category/{category}/subcategory/{subcategory}")
    public ResponseEntity<List<Algorithm>> getAlgorithmsByCategoryAndSubcategory(
            @PathVariable String category,
            @PathVariable String subcategory) {
        List<Algorithm> algorithms = algorithmService.getAlgorithmsByCategoryAndSubcategory(category, subcategory);
        return ResponseEntity.ok(algorithms);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = algorithmService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{category}/subcategories")
    public ResponseEntity<List<String>> getSubcategoriesByCategory(@PathVariable String category) {
        List<String> subcategories = algorithmService.getSubcategoriesByCategory(category);
        return ResponseEntity.ok(subcategories);
    }

    @PostMapping
    public ResponseEntity<Algorithm> createAlgorithm(@RequestBody Algorithm algorithm) {
        Algorithm savedAlgorithm = algorithmService.saveAlgorithm(algorithm);
        return ResponseEntity.ok(savedAlgorithm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Algorithm> updateAlgorithm(@PathVariable Long id, @RequestBody Algorithm algorithm) {
        Optional<Algorithm> existingAlgorithm = algorithmService.getAlgorithmById(id);
        if (existingAlgorithm.isPresent()) {
            algorithm.setId(id);
            Algorithm updatedAlgorithm = algorithmService.saveAlgorithm(algorithm);
            return ResponseEntity.ok(updatedAlgorithm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlgorithm(@PathVariable Long id) {
        algorithmService.deleteAlgorithm(id);
        return ResponseEntity.noContent().build();
    }
}