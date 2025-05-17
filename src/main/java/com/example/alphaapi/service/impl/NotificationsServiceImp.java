package com.example.alphaapi.service.impl;

import com.example.alphaapi.model.dto.AddNotificationDto;
import com.example.alphaapi.model.dto.NotificationDto;
import com.example.alphaapi.model.entity.Notifications;
import com.example.alphaapi.model.mapper.NotificationsMapper;
import com.example.alphaapi.repo.NotificationsRepo;
import com.example.alphaapi.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationsServiceImp implements NotificationsService {

    public final NotificationsRepo notificationsRepo;

    private final NotificationsMapper notificationMapper;

//    private final FirebaseMessaging firebaseMessaging;

    @Override
    public List<NotificationDto> findAllNotifications() {
        return notificationMapper.toDtos(notificationsRepo.findAll());
    }

    @Override
    public void sendNotificationToAll(AddNotificationDto dto) {

        Notifications notifications = notificationMapper.toAddEntity(dto);

//        Notification notification = Notification.builder()
//                .setTitle(dto.getTitle())
//                .setBody(dto.getBody())
//                .build();

//        Message message = Message.builder()
//                .setNotification(notification)
//                .setTopic("all-student")
//                .build();

//        try {
//            firebaseMessaging.send(message);
//            System.out.println("done");
            notificationsRepo.save(notifications);
//        } catch (FirebaseMessagingException e) {
//            e.printStackTrace();
//            System.out.println("error");
//        }
    }

    @Override
    public void deleteById(int id) {
        notificationsRepo.deleteById(id);
    }
}
