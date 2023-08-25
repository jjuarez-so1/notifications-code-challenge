package com.jjuarez.gila.notification.strategy;

import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EmailNotificationStrategyTest {

    @Mock
    private SentNotificationRepository sentNotificationRepository;

    @Mock
    private BroadcastChannelRepository broadcastChannelRepository;

    private EmailNotificationStrategy emailStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailStrategy = new EmailNotificationStrategy(sentNotificationRepository, broadcastChannelRepository);
    }

    @Test
    void testSendNotification() {
        final User user = new User();
        final Notification notification = new Notification();

        emailStrategy.sendNotification(user, notification);

        verify(broadcastChannelRepository, times(1)).findByName("EMAIL");
        verify(sentNotificationRepository, times(1)).save(any(SentNotification.class));
    }
}