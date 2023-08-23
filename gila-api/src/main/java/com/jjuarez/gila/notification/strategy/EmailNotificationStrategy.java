package com.jjuarez.gila.notification.strategy;

import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.SentNotification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.notification.NotificationStrategy;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import com.jjuarez.gila.utility.DelaySimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class EmailNotificationStrategy implements NotificationStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(EmailNotificationStrategy.class);
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final SentNotificationRepository sentNotificationRepository;

    public EmailNotificationStrategy(SentNotificationRepository notificationRepository,
                                     BroadcastChannelRepository broadcastChannelRepository
    ) {
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.sentNotificationRepository = notificationRepository;
    }

    @Override
    public void sendNotification(User user, Notification notification) {
        callToExternalApi(user, notification);
    }

    private void callToExternalApi(User user, Notification notification) {
        DelaySimulator.simulateDelay();
        LOG.info("Email notification sent to: {}", user.getEmail());

        BroadcastChannel broadcastChannel = broadcastChannelRepository.findByName("EMAIL");

        SentNotification sentNotification = new SentNotification();
        sentNotification.setTopic(notification.getTopic());
        sentNotification.setSentTime(Instant.now());
        sentNotification.setChannel(broadcastChannel);
        sentNotification.setNotification(notification);
        sentNotification.setSentToUser(user);

        sentNotificationRepository.save(sentNotification);
    }
}
