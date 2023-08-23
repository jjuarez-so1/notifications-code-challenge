package com.jjuarez.gila.service;

import com.jjuarez.gila.dto.NotificationDTO;
import com.jjuarez.gila.request.NotificationRequest;

import java.util.List;

public interface NotificationService {
    void broadcast(NotificationRequest request);
    List<NotificationDTO> getLast5Notifications();
}
