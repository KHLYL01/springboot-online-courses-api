package com.example.alphaapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int totalScore;
    private boolean valid;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;
}
