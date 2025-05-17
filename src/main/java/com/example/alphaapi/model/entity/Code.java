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
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String symbol;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private CodeGroup codeGroup;

    private String macDeviceCode;

    private CodeStatus codeStatus;


    private LocalDateTime enableDate;
}
