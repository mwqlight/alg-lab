package com.alglab.algorithm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimationStepRepository extends JpaRepository<AnimationStep, Long> {
    List<AnimationStep> findByAlgorithmIdOrderByStepNumber(Long algorithmId);
}