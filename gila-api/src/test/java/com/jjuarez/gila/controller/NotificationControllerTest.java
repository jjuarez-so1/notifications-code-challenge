package com.jjuarez.gila.controller;

import com.jjuarez.gila.request.NotificationRequest;
import com.jjuarez.gila.response.ApiResponse;
import com.jjuarez.gila.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void testSendNotification() {
        /*NotificationRequest request = new NotificationRequest("Category", "Message");

        ApiResponse expectedResponse = new ApiResponse("Notifications sent successfully.", "Success");
        ResponseEntity<ApiResponse> expectedResponseEntity = ResponseEntity.ok(expectedResponse);

        doNothing().when(notificationService).sendNotifications(request);

        ResponseEntity<ApiResponse> actualResponseEntity = notificationController.sendNotification(request);

        assertEquals(expectedResponseEntity.getStatusCode(), actualResponseEntity.getStatusCode());
        assertEquals(Objects.requireNonNull(expectedResponseEntity.getBody()).getMessage(),
                Objects.requireNonNull(actualResponseEntity.getBody()).getMessage());
        assertEquals(expectedResponseEntity.getBody().getStatus(), actualResponseEntity.getBody().getStatus());

        verify(notificationService, times(1)).sendNotifications(request);*/
    }
}