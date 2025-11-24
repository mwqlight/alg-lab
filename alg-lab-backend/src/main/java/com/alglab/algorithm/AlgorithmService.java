package com.alglab.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlgorithmService {
    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Cacheable(value = "algorithms", key = "'all'")
    public List<Algorithm> getAllAlgorithms() {
        return algorithmRepository.findAll();
    }

    @Cacheable(value = "algorithms", key = "#id")
    public Optional<Algorithm> getAlgorithmById(Long id) {
        return algorithmRepository.findById(id);
    }

    @Cacheable(value = "algorithms", key = "#name")
    public Optional<Algorithm> getAlgorithmByName(String name) {
        return algorithmRepository.findByName(name);
    }

    @Cacheable(value = "algorithms", key = "#abbreviation")
    public Optional<Algorithm> getAlgorithmByAbbreviation(String abbreviation) {
        return algorithmRepository.findByAbbreviation(abbreviation);
    }

    @Cacheable(value = "algorithms", key = "'category_' + #category")
    public List<Algorithm> getAlgorithmsByCategory(String category) {
        return algorithmRepository.findByCategory(category);
    }

    @Cacheable(value = "algorithms", key = "'category_' + #category + '_sub_' + #subcategory")
    public List<Algorithm> getAlgorithmsByCategoryAndSubcategory(String category, String subcategory) {
        return algorithmRepository.findByCategoryAndSubcategory(category, subcategory);
    }

    @Cacheable(value = "categories", key = "'all'")
    public List<String> getAllCategories() {
        return algorithmRepository.findAllCategories();
    }

    @Cacheable(value = "categories", key = "#category")
    public List<String> getSubcategoriesByCategory(String category) {
        return algorithmRepository.findSubcategoriesByCategory(category);
    }

    public Algorithm saveAlgorithm(Algorithm algorithm) {
        Algorithm saved = algorithmRepository.save(algorithm);
        // 清除相关缓存
        return saved;
    }

    public void deleteAlgorithm(Long id) {
        algorithmRepository.deleteById(id);
        // 清除相关缓存
    }
}