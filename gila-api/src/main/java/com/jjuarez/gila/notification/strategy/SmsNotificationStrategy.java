package com.jjuarez.gila.notification.strategy;

import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.exception.InvalidPhoneNumberException;
import com.jjuarez.gila.notification.NotificationStrategy;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import com.jjuarez.gila.utility.DelaySimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class SmsNotificationStrategy implements NotificationStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(SmsNotificationStrategy.class);

    private final SentNotificationRepository sentNotificationRepository;
    private final BroadcastChannelRepository broadcastChannelRepository;
    private static final int VALID_PHONE_LENGTH = 10;

    public SmsNotificationStrategy(SentNotificationRepository sentNotificationService,
                                   BroadcastChannelRepository broadcastChannelRepository) {
        this.sentNotificationRepository = sentNotificationService;
        this.broadcastChannelRepository = broadcastChannelRepository;
    }


    @Override
    public void sendNotification(User user, Notification notification) {
        if (user.getPhone().length() != VALID_PHONE_LENGTH) {
            throw new InvalidPhoneNumberException("Invalid phone number length");
        }
        callToExternalAPI(user, notification);
    }

    private void callToExternalAPI(User user, Notification notification) {
        DelaySimulator.simulateDelay();
        LOG.info("SMS notification sent to: {}", user.getPhone());

        BroadcastChannel broadcastChannel = broadcastChannelRepository.findByName("SMS");

        SentNotification sentNotification = new SentNotification();
        sentNotification.setTopic(notification.getTopic());
        sentNotification.setSentTime(Instant.now());
        sentNotification.setChannel(broadcastChannel);
        sentNotification.setNotification(notification);
        sentNotification.setSentToUser(user);

        sentNotificationRepository.save(sentNotification);
    }
}
