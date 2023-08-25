package com.jjuarez.gila.notification;

import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.notification.enums.NotificationChannelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationStrategyContextTest {

    @Mock
    private NotificationStrategyFactory strategyFactory;

    @Mock
    private NotificationStrategy smsStrategy;

    @Mock
    private NotificationStrategy emailStrategy;

    @Mock
    private NotificationStrategy pushNotificationStrategy;

    private NotificationStrategyContext strategyContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        strategyContext = new NotificationStrategyContext(strategyFactory);
    }

    @Test
    void testSendNotification() {
        final User user = new User();
        final Topic topic = new Topic();
        final Notification notification = new Notification();
        final List<BroadcastChannel> preferredChannels = new ArrayList<>();
        preferredChannels.add(new BroadcastChannel(1L, "SMS"));
        preferredChannels.add(new BroadcastChannel(2L, "EMAIL"));

        user.setPreferredChannels(preferredChannels);

        when(strategyFactory.createSmsNotificationChannel()).thenReturn(smsStrategy);
        when(strategyFactory.createEmailNotificationChannel()).thenReturn(emailStrategy);

        strategyContext.sendNotification(user, topic, "Test message", notification);

        verify(smsStrategy, times(1)).sendNotification(user, notification);
        verify(emailStrategy, times(1)).sendNotification(user, notification);
        verify(pushNotificationStrategy, never()).sendNotification(any(), any());
    }

    @Test
    void testGetStrategyForChannelType() {
        when(strategyFactory.createSmsNotificationChannel()).thenReturn(smsStrategy);
        when(strategyFactory.createEmailNotificationChannel()).thenReturn(emailStrategy);
        when(strategyFactory.createPushNotificationChannel()).thenReturn(pushNotificationStrategy);

        NotificationStrategy smsStrategy = strategyContext.getStrategyForChannelType(NotificationChannelType.SMS);
        NotificationStrategy emailStrategy = strategyContext.getStrategyForChannelType(NotificationChannelType.EMAIL);
        NotificationStrategy pushStrategy = strategyContext.getStrategyForChannelType(NotificationChannelType.PUSH_NOTIFICATION);

        verify(strategyFactory, times(1)).createSmsNotificationChannel();
        verify(strategyFactory, times(1)).createEmailNotificationChannel();
        verify(strategyFactory, times(1)).createPushNotificationChannel();
    }

    @Test
    void testGetStrategyForChannelTypeNotFound() {
        when(strategyFactory.createSmsNotificationChannel()).thenReturn(smsStrategy);
        when(strategyFactory.createEmailNotificationChannel()).thenReturn(emailStrategy);
        when(strategyFactory.createPushNotificationChannel()).thenReturn(pushNotificationStrategy);
    }
}