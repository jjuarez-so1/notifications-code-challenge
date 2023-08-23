package com.jjuarez.gila.repository;

import com.jjuarez.gila.entity.SentNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentNotificationRepository extends JpaRepository<SentNotification, Long> {
}
