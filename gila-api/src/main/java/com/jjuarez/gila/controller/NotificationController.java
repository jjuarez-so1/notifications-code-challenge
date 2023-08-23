package com.jjuarez.gila.controller;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.dto.NotificationDTO;
import com.jjuarez.gila.request.NotificationRequest;
import com.jjuarez.gila.response.ApiResponse;
import com.jjuarez.gila.service.NotificationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(NotificationController.BASE_PATH)
public class NotificationController {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationController.class);
    private static final String SEND_NOTIFICATION_ENDPOINT = "/send";
    private static final String LAST_NOTIFICATIONS_ENDPOINT = "/last";
    public static final String BASE_PATH = "/api/notifications";
    private final NotificationService notificationService;

    public NotificationController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(SEND_NOTIFICATION_ENDPOINT)
    public ResponseEntity<ApiResponse> sendNotification(final @Valid @RequestBody NotificationRequest request) {
        LOG.info("Request received for broadcast: {}.", request);
        final ApiResponse response = new ApiResponse(ApiConstants.DEFAULT_SUCCESS_MESSAGE,
                ApiConstants.STATUS_PENDING);
        CompletableFuture.runAsync(() -> notificationService.broadcast(request));
        return ResponseEntity.ok(response);
    }

    @GetMapping(LAST_NOTIFICATIONS_ENDPOINT)
    public ResponseEntity<List<NotificationDTO>> getLastNotifications() {
        LOG.info("Retrieving last 5 notifications.");
        final List<NotificationDTO> lastNotifications = notificationService.getLast5Notifications();
        return ResponseEntity.ok(lastNotifications);
    }
}
