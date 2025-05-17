package com.example.alphaapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdfFile {
    @Id
    private String id;
    private String title;
    private String filePath;
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;
}
