package com.example.alphaapi.controller;

import com.example.alphaapi.model.dto.AddNotificationDto;
import com.example.alphaapi.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notifications")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationsService notificationsServiceService;

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAllNotifications() {
        return ResponseEntity.ok(notificationsServiceService.findAllNotifications());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> sendNotificationToAll(@RequestBody AddNotificationDto dto) {
        notificationsServiceService.sendNotificationToAll(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        notificationsServiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
