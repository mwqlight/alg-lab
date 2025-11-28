package com.alglab.algorithm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animation_steps")
public class AnimationStep {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "algorithm_id")
    private Long algorithmId;
    
    @Column(name = "step_number")
    private Integer stepNumber;
    
    @Column(name = "array_state", length = 1000)
    private String arrayState;
    
    @Column(name = "compared_indices", length = 100)
    private String comparedIndices;
    
    @Column(name = "swapped")
    private Boolean swapped;
}