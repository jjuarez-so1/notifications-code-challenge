package com.jjuarez.gila.controller;

import com.jjuarez.gila.dto.NotificationDTO;
import com.jjuarez.gila.request.NotificationRequest;
import com.jjuarez.gila.response.ApiResponse;
import com.jjuarez.gila.service.NotificationService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void testSendNotification() {
        final NotificationRequest request = new NotificationRequest("FILMS", "Notification message");
        final ApiResponse expectedResponse = new ApiResponse("Notification request received.", "Pending");

        doNothing().when(notificationService).broadcast(request);

        final ResponseEntity<ApiResponse> actualResponseEntity = notificationController.sendNotification(request);

        assertEquals(expectedResponse.getMessage(), Objects
                .requireNonNull(actualResponseEntity.getBody()).getMessage());
        assertEquals(expectedResponse.getStatus(), actualResponseEntity.getBody().getStatus());
        assertEquals(HttpStatus.OK.value(), actualResponseEntity.getStatusCode().value());

        verify(notificationService, times(1)).broadcast(request);
    }

    @Test
    @Disabled
    void testGetLastNotifications() {
        final List<NotificationDTO> expectedNotifications = new ArrayList<>();
        final NotificationDTO notification1 = new NotificationDTO();
        notification1.setId(1L);
        final NotificationDTO notification2 = new NotificationDTO();
        notification1.setId(2L);

        expectedNotifications.add(notification1);
        expectedNotifications.add(notification2);

        when(notificationService.getLast5Notifications()).thenReturn(expectedNotifications);

        ResponseEntity<List<NotificationDTO>> actualResponseEntity = notificationController.getLastNotifications();

        assertEquals(expectedNotifications, actualResponseEntity.getBody());
        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());

        verify(notificationService).getLast5Notifications();
    }
}