package com.jjuarez.gila.notification.strategy;

import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PushNotificationStrategyTest {

    @Mock
    private SentNotificationRepository sentNotificationRepository;

    @Mock
    private BroadcastChannelRepository broadcastChannelRepository;

    private PushNotificationStrategy pushStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pushStrategy = new PushNotificationStrategy(broadcastChannelRepository, sentNotificationRepository);
    }

    @Test
    void testSendNotification() {
        final User user = new User.Builder().name("Joseph").build();
        final Topic topic = new Topic(1L, "Topic name");
        final Notification notification = new Notification();
        notification.setTopic(topic);

        assertDoesNotThrow(() -> pushStrategy.sendNotification(user, notification));
        verify(broadcastChannelRepository, times(1)).findByName("PUSH_NOTIFICATION");
        verify(sentNotificationRepository, times(1)).save(any(SentNotification.class));
    }
}
