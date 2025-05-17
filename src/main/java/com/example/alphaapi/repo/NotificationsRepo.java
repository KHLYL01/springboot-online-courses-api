package com.example.alphaapi.repo;

import com.example.alphaapi.model.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepo extends JpaRepository<Notifications, Integer> {
}
