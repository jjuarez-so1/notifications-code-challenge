package com.jjuarez.gila.notification;

import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;

public interface NotificationStrategy {
    void sendNotification(User user, Notification notification);
}
