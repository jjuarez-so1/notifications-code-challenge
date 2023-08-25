package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.dto.KPIsDTO;
import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.repository.NotificationRepository;
import com.jjuarez.gila.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class KpiServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    private KpiServiceImpl kpiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kpiService = new KpiServiceImpl(notificationRepository, userRepository);
    }

    @Test
    void testGetKpisForLastNotificationWithNotification() {
        final Notification notification = new Notification();
        final SentNotification sentNotifications = new SentNotification();
        sentNotifications.setChannel(new BroadcastChannel(1L, ApiConstants.BROADCAST_CHANNEL_EMAIL));
        notification.addSentNotification(sentNotifications);

        when(notificationRepository.findTopByOrderByStartTimeDesc()).thenReturn(notification);
        when(userRepository.count()).thenReturn(10L);

        KPIsDTO kpisDTO = kpiService.getKpisForLastNotification();

        assertNotNull(kpisDTO);
        assertEquals(10, kpisDTO.getUsers().getQuantity());
        assertEquals(1, kpisDTO.getEmailNotifications().getQuantity());
    }

    @Test
    void testGetKpisForLastNotificationWithoutNotification() {
        Notification notification = new Notification();

        when(notificationRepository.findTopByOrderByStartTimeDesc()).thenReturn(notification);


        KPIsDTO kpisDTO = kpiService.getKpisForLastNotification();

        assertEquals(0, kpisDTO.getUsers().getQuantity());
        assertEquals(0, kpisDTO.getEmailNotifications().getQuantity());
        assertEquals(0, kpisDTO.getPushNotifications().getQuantity());
        assertEquals(0, kpisDTO.getSmsNotifications().getQuantity());
    }
}
