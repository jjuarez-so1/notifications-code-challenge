package com.jjuarez.gila.notification.strategy;

import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.exception.InvalidPhoneNumberException;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SmsNotificationStrategyTest {

    @Mock
    private SentNotificationRepository sentNotificationRepository;

    @Mock
    private BroadcastChannelRepository broadcastChannelRepository;

    private SmsNotificationStrategy smsStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        smsStrategy = new SmsNotificationStrategy(sentNotificationRepository, broadcastChannelRepository);
    }

    @Test
    void testSendNotification_ValidPhoneNumber() {
        final User user = new User.Builder().phone("2722368978").build();
        final Notification notification = new Notification();

        assertDoesNotThrow(() -> smsStrategy.sendNotification(user, notification));
        verify(broadcastChannelRepository, times(1)).findByName("SMS");
        verify(sentNotificationRepository, times(1)).save(any(SentNotification.class));
    }

    @Test
    void testSendNotification_InvalidPhoneNumber() {
        final User user = new User.Builder().phone("272").build();
        final Notification notification = new Notification();

        assertThrows(InvalidPhoneNumberException.class, () -> smsStrategy.sendNotification(user, notification));
        verify(broadcastChannelRepository, never()).findByName(anyString());
        verify(sentNotificationRepository, never()).save(any(SentNotification.class));
    }
}