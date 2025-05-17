package com.example.alphaapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int numberOfStudent;
    private int price;
    private boolean valid;
    private boolean free;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
