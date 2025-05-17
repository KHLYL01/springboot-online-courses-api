package com.example.alphaapi.service;

import com.example.alphaapi.model.dto.AddNotificationDto;
import com.example.alphaapi.model.dto.NotificationDto;

import java.util.List;


public interface NotificationsService {

    List<NotificationDto> findAllNotifications();

    void sendNotificationToAll(AddNotificationDto dto);

    void deleteById(int id);
}
