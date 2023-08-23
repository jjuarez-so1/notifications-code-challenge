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

public class PushNotificationStrategy implements NotificationStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(PushNotificationStrategy.class);
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final SentNotificationRepository sentNotificationRepository;

    public PushNotificationStrategy(BroadcastChannelRepository broadcastChannelRepository,
                                    SentNotificationRepository sentNotificationRepository) {
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.sentNotificationRepository = sentNotificationRepository;
    }

    @Override
    public void sendNotification(User user, Notification notification) {
        DelaySimulator.simulateDelay();
        callToExternalApi(user, notification);
    }

    private void callToExternalApi(User user, Notification notification) {
        DelaySimulator.simulateDelay();
        LOG.info("Push notification sent to user with id: {}, with title: {}, and body: {}",
                user.getId(), notification.getTopic().getName(), notification.getMessage());

        BroadcastChannel broadcastChannel = broadcastChannelRepository.findByName("PUSH_NOTIFICATION");

        SentNotification sentNotification = new SentNotification();
        sentNotification.setTopic(notification.getTopic());
        sentNotification.setSentTime(Instant.now());
        sentNotification.setChannel(broadcastChannel);
        sentNotification.setNotification(notification);
        sentNotification.setSentToUser(user);

        sentNotificationRepository.save(sentNotification);
    }
}
