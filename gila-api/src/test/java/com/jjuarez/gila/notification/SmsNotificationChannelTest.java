package com.jjuarez.gila.notification;

import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.exception.InvalidPhoneNumberException;
import com.jjuarez.gila.notification.strategy.SmsNotificationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SmsNotificationChannelTest {

    @Mock
    private Logger logger;

    @InjectMocks
    private SmsNotificationStrategy smsNotificationChannel;

    private User validUser;
    private User invalidUser;

    @BeforeEach
    void setUp() {
        validUser = new User();
        validUser.setPhone("1234567890");

        invalidUser = new User();
        invalidUser.setPhone("123");
    }

    @Test
    void testSendNotificationWithValidUser() {
        /*Topic topic = new Topic(1L, "SPORTS");
        String message = "Final score";

        // fix this shit
        smsNotificationChannel.sendNotification(validUser, null);*/
    }

    @Test
    void testSendNotificationWithInvalidUser() {
        Topic topic = new Topic(1L, "SPORTS");
        String message = "Final score";

        assertThrows(InvalidPhoneNumberException.class,
                // fix this shit
                () -> smsNotificationChannel.sendNotification(invalidUser, null));
        verify(logger, never()).info(anyString(), (Object) any());
    }
}