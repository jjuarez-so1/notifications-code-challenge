package com.jjuarez.gila.controller;

import com.jjuarez.gila.dto.MessageDTO;
import com.jjuarez.gila.dto.PaginatedMessageDTO;
import com.jjuarez.gila.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MessageController.BASE_PATH)
public class MessageController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
    public static final String BASE_PATH = "/api/messages";
    private static final String BY_NOTIFICATION_ENDPOINT = "/by-notification";

    private final MessageService messageService;

    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(BY_NOTIFICATION_ENDPOINT)
    public ResponseEntity<PaginatedMessageDTO> getMessagesByNotification(
            final @RequestParam Long notificationId,
            final @RequestParam int page,
            final @RequestParam int pageSize
    ) {
        LOG.info("Getting messages by notification");
        final PaginatedMessageDTO paginatedMessageDTO = messageService.getMessagesByNotification(
                notificationId, page, pageSize
        );
        return ResponseEntity.ok(paginatedMessageDTO);
    }
}
