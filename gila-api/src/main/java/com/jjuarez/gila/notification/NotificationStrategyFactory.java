package com.jjuarez.gila.notification;

import com.jjuarez.gila.notification.strategy.EmailNotificationStrategy;
import com.jjuarez.gila.notification.strategy.PushNotificationStrategy;
import com.jjuarez.gila.notification.strategy.SmsNotificationStrategy;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.SentNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationStrategyFactory {

    private final SentNotificationRepository sentNotificationRepository;
    private final BroadcastChannelRepository broadcastChannelRepository;

    @Autowired
    public NotificationStrategyFactory(SentNotificationRepository sentNotificationRepository,
                                       BroadcastChannelRepository broadcastChannelRepository) {
        this.sentNotificationRepository = sentNotificationRepository;
        this.broadcastChannelRepository = broadcastChannelRepository;
    }

    public NotificationStrategy createEmailNotificationChannel() {
        return new EmailNotificationStrategy(sentNotificationRepository, broadcastChannelRepository);
    }

    public NotificationStrategy createPushNotificationChannel() {
        return new PushNotificationStrategy(broadcastChannelRepository, sentNotificationRepository);
    }

    public NotificationStrategy createSmsNotificationChannel() {
        return new SmsNotificationStrategy(sentNotificationRepository, broadcastChannelRepository);
    }
}
