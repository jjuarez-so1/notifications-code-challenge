package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.dto.MessageDTO;
import com.jjuarez.gila.dto.PaginatedMessageDTO;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.exception.NotificationNotFoundException;
import com.jjuarez.gila.repository.NotificationRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import com.jjuarez.gila.service.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final SentNotificationRepository sentNotificationRepository;
    private final NotificationRepository notificationRepository;

    public MessageServiceImpl(final SentNotificationRepository sentNotificationRepository,
                              final NotificationRepository notificationRepository) {
        this.sentNotificationRepository = sentNotificationRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public PaginatedMessageDTO getMessagesByNotification(final Long notificationId, final int page, final int pageSize) {
        final Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found"));

        final int adjustedPage = page - 1;

        final Sort sort = Sort.by(Sort.Direction.DESC, "sentTime");

        final Page<SentNotification> sentNotificationPage = sentNotificationRepository.findByNotification(
                notification,
                PageRequest.of(adjustedPage, pageSize, sort)
        );

        final List<MessageDTO> messageDTOs = sentNotificationPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        final long totalCount = sentNotificationPage.getTotalElements();

        return new PaginatedMessageDTO(messageDTOs, totalCount);
    }

    private MessageDTO convertToDTO(final SentNotification sentNotification) {
        final MessageDTO messageDTO = new MessageDTO();
        if (sentNotification.getTopic() != null) {
            messageDTO.setChannel(sentNotification.getChannel().getName());
        }

        if (sentNotification.getSentToUser() != null) {
            messageDTO.setUserEmail(sentNotification.getSentToUser().getEmail());
        }

        if(sentNotification.getSentTime() != null) {
            messageDTO.setSentTime(sentNotification.getSentTime());
        }
        messageDTO.setUserId(sentNotification.getSentToUser().getId());

        return messageDTO;
    }
}
