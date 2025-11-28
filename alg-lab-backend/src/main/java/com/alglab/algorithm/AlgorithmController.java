package com.alglab.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/algorithms")
@CrossOrigin(origins = "http://localhost:3000")
public class AlgorithmController {
    
    @Autowired
    private AlgorithmRepository algorithmRepository;
    
    @Autowired
    private AnimationStepRepository animationStepRepository;
    
    // 获取所有算法
    @GetMapping
    public List<Algorithm> getAllAlgorithms() {
        return algorithmRepository.findAll();
    }
    
    // 根据分类获取算法
    @GetMapping("/category/{category}")
    public List<Algorithm> getAlgorithmsByCategory(@PathVariable String category) {
        return algorithmRepository.findByCategory(category);
    }
    
    // 根据ID获取算法详情
    @GetMapping("/{id}")
    public Algorithm getAlgorithmById(@PathVariable Long id) {
        return algorithmRepository.findById(id).orElse(null);
    }
    
    // 获取算法的动画步骤
    @GetMapping("/{id}/animation")
    public List<AnimationStep> getAnimationSteps(@PathVariable Long id) {
        return animationStepRepository.findByAlgorithmIdOrderByStepNumber(id);
    }
}