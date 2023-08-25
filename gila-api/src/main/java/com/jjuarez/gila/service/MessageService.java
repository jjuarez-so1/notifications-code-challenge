package com.jjuarez.gila.service;

import com.jjuarez.gila.dto.MessageDTO;
import com.jjuarez.gila.dto.PaginatedMessageDTO;

import java.util.List;

public interface MessageService {
    PaginatedMessageDTO getMessagesByNotification(Long notificationId, int page, int pageSize);
}
