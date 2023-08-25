package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.dto.NotificationDTO;
import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.notification.NotificationStrategyContext;
import com.jjuarez.gila.repository.NotificationRepository;
import com.jjuarez.gila.request.NotificationRequest;
import com.jjuarez.gila.service.TopicService;
import com.jjuarez.gila.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationServiceImplTest {

    @Mock
    private NotificationStrategyContext notificationStrategyContext;

    @Mock
    private TopicService topicService;

    @Mock
    private UserService userService;

    @Mock
    private NotificationRepository notificationRepository;

    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationServiceImpl(
                notificationStrategyContext, topicService, userService, notificationRepository);
    }

    @Test
    void testBroadcast() {
        final NotificationRequest request = new NotificationRequest(ApiConstants.CATEGORY_FILMS,
                "Notification message");
        final Topic topic = new Topic(1L, ApiConstants.CATEGORY_FILMS);
        final BroadcastChannel broadcastChannel = new BroadcastChannel(1L, ApiConstants.BROADCAST_CHANNEL_EMAIL);

        final User user1 = new User.Builder().name("joseph").preferredChannels(List.of(broadcastChannel))
                .subscribedTopics(List.of(topic)).build();
        final User user2 = new User.Builder().name("john").preferredChannels(List.of(broadcastChannel))
                .subscribedTopics(List.of(topic)).build();
        final Notification notification = new Notification();
        notification.setId(1L);

        when(topicService.findByName(request.category())).thenReturn(topic);
        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));
        when(notificationRepository.save(any())).thenReturn(notification);

        notificationService.broadcast(request);

        verify(notificationStrategyContext, times(2))
                .sendNotification(any(), any());
    }

    @Test
    void testGetLast5Notifications() {
        final List<Notification> notifications = new ArrayList<>();
        final Topic topic = new Topic(1L, ApiConstants.CATEGORY_FILMS);
        final Notification notification = new Notification();
        notification.setTopic(topic);
        notifications.add(notification);

        when(notificationRepository.findTop5ByOrderByStartTimeDesc()).thenReturn(notifications);
        List<NotificationDTO> notificationDTOs = notificationService.getLast5Notifications();

        assertEquals(notifications.size(), notificationDTOs.size());
    }
}
