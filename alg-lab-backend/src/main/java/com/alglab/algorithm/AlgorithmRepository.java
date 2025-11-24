package com.alglab.algorithm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {
    Optional<Algorithm> findByName(String name);
    Optional<Algorithm> findByAbbreviation(String abbreviation);
    List<Algorithm> findByCategory(String category);
    List<Algorithm> findByCategoryAndSubcategory(String category, String subcategory);

    @Query("SELECT DISTINCT a.category FROM Algorithm a ORDER BY a.category")
    List<String> findAllCategories();

    @Query("SELECT DISTINCT a.subcategory FROM Algorithm a WHERE a.category = ?1 ORDER BY a.subcategory")
    List<String> findSubcategoriesByCategory(String category);
}