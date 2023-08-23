package com.jjuarez.gila.notification;

import com.jjuarez.gila.entity.BroadcastChannel;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.exception.BroadcastChannelNotFoundException;
import com.jjuarez.gila.notification.enums.NotificationChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationStrategyContext {

    private final NotificationStrategyFactory strategyFactory;

    @Autowired
    public NotificationStrategyContext(NotificationStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public void sendNotification(User user, Topic topic, String message, Notification notification) {
        for (BroadcastChannel channel : user.getPreferredChannels()) {
            NotificationChannelType channelType = NotificationChannelType.valueOf(channel.getName());
            NotificationStrategy strategy = getStrategyForChannelType(channelType);
            strategy.sendNotification(user, notification);
        }
    }

    private NotificationStrategy getStrategyForChannelType(NotificationChannelType channelType) {
        switch (channelType) {
            case SMS:
                return strategyFactory.createSmsNotificationChannel();
            case EMAIL:
                return strategyFactory.createEmailNotificationChannel();
            case PUSH_NOTIFICATION:
                return strategyFactory.createPushNotificationChannel();
            default:
                throw new BroadcastChannelNotFoundException("Notification strategy not found for channel: " + channelType);
        }
    }
}
