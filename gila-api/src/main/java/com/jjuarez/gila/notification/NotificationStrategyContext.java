package com.jjuarez.gila.notification;

import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.exception.BroadcastChannelNotFoundException;
import com.jjuarez.gila.notification.enums.NotificationChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationStrategyContext {

    private final NotificationStrategyFactory strategyFactory;

    @Autowired
    public NotificationStrategyContext(final NotificationStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public void sendNotification(final User user, final Notification notification) {
        user.getPreferredChannels().forEach(channel -> {
            final NotificationChannelType channelType = NotificationChannelType.valueOf(channel.getName());
            final NotificationStrategy strategy = getStrategyForChannelType(channelType);
            strategy.sendNotification(user, notification);
        });
    }

    NotificationStrategy getStrategyForChannelType(NotificationChannelType channelType) {
        return switch (channelType) {
            case SMS -> strategyFactory.createSmsNotificationChannel();
            case EMAIL -> strategyFactory.createEmailNotificationChannel();
            case PUSH_NOTIFICATION -> strategyFactory.createPushNotificationChannel();
            default ->
                    throw new BroadcastChannelNotFoundException("Notification strategy not found for channel: " + channelType);
        };
    }
}
