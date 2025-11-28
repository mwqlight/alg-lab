package com.alglab.algorithm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "algorithms")
public class Algorithm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @Column(name = "time_complexity")
    private String timeComplexity;
    
    @Column(name = "space_complexity")
    private String spaceComplexity;
    
    @Column(length = 2000)
    private String analysis;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "code_example", length = 5000)
    private String codeExample;
}