package com.example.alphaapi.model.entity;

import com.example.alphaapi.model.enums.CodeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String symbol;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String macDeviceCode;

    private CodeStatus codeStatus;

    private boolean paid;

    private LocalDateTime enableDate;
}
