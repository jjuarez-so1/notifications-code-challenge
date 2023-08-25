package com.jjuarez.gila.repository;

import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentNotificationRepository extends JpaRepository<SentNotification, Long> {
    Page<SentNotification> findByNotification(Notification notification, PageRequest of);
}
