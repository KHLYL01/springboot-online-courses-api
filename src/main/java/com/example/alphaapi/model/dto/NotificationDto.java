package com.example.alphaapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder

public class NotificationDto {
    private int id;
    private String title;
    private String body;
    private LocalDateTime createdDate;
}
