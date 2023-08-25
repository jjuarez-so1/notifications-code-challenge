package com.jjuarez.gila.notification;

import com.jjuarez.gila.notification.strategy.EmailNotificationStrategy;
import com.jjuarez.gila.notification.strategy.PushNotificationStrategy;
import com.jjuarez.gila.notification.strategy.SmsNotificationStrategy;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class NotificationStrategyFactoryTest {

    @Mock
    private SentNotificationRepository sentNotificationRepository;

    @Mock
    private BroadcastChannelRepository broadcastChannelRepository;

    private NotificationStrategyFactory strategyFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        strategyFactory = new NotificationStrategyFactory(sentNotificationRepository, broadcastChannelRepository);
    }

    @Test
    void testCreateEmailNotificationChannel() {
        NotificationStrategy emailStrategy = strategyFactory.createEmailNotificationChannel();
        assertEquals(EmailNotificationStrategy.class, emailStrategy.getClass());
    }

    @Test
    void testCreatePushNotificationChannel() {
        NotificationStrategy pushStrategy = strategyFactory.createPushNotificationChannel();
        assertEquals(PushNotificationStrategy.class, pushStrategy.getClass());
    }

    @Test
    void testCreateSmsNotificationChannel() {
        NotificationStrategy smsStrategy = strategyFactory.createSmsNotificationChannel();
        assertEquals(SmsNotificationStrategy.class, smsStrategy.getClass());
    }
}
