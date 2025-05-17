package com.example.alphaapi.model.mapper;

import com.example.alphaapi.model.dto.AddNotificationDto;
import com.example.alphaapi.model.dto.NotificationDto;
import com.example.alphaapi.model.entity.Notifications;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationsMapper {

    public List<NotificationDto> toDtos(List<Notifications> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public NotificationDto toDto(Notifications entity) {
        return NotificationDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    public Notifications toEntity(NotificationDto dto) {
        return Notifications.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }

    public Notifications toAddEntity(AddNotificationDto dto) {
        return Notifications.builder()
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }
}
