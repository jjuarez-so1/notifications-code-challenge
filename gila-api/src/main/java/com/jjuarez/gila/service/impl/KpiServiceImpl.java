package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.dto.KPITypeDTO;
import com.jjuarez.gila.dto.KPIsDTO;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.repository.BroadcastChannelRepository;
import com.jjuarez.gila.repository.NotificationRepository;
import com.jjuarez.gila.repository.TopicRepository;
import com.jjuarez.gila.repository.UserRepository;
import com.jjuarez.gila.service.KpiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KpiServiceImpl implements KpiService {
    private static final Logger LOG = LoggerFactory.getLogger(KpiServiceImpl.class);

    private final NotificationRepository notificationRepository;
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Autowired
    public KpiServiceImpl(NotificationRepository notificationRepository,
                          BroadcastChannelRepository broadcastChannelRepository,
                          TopicRepository topicRepository,
                          UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public KPIsDTO getKpisForLastNotification() {
        Notification lastNotification = notificationRepository.findTopByOrderByStartTimeDesc();

        if (lastNotification != null) {
            return calculateKPIs(lastNotification);
        }

        return new KPIsDTO();
    }

    private KPIsDTO calculateKPIs(Notification notification) {
        KPIsDTO kpisDTO = new KPIsDTO();

        long totalUsers = userRepository.count();

        long totalEmailNotifications = notification.getSentNotifications().stream()
                .filter(sentNotification -> sentNotification.getChannel().getName().equalsIgnoreCase("EMAIL"))
                .count();

        long totalSmsNotifications = notification.getSentNotifications().stream()
                .filter(sentNotification -> sentNotification.getChannel().getName().equalsIgnoreCase("SMS"))
                .count();

        long totalPushNotifications = notification.getSentNotifications().stream()
                .filter(sentNotification -> sentNotification.getChannel().getName().equalsIgnoreCase("PUSH_NOTIFICATION"))
                .count();

        KPITypeDTO users = new KPITypeDTO("USERS", totalUsers);
        KPITypeDTO emailNotifications = new KPITypeDTO("EMAIL_NOTIFICATIONS", totalEmailNotifications);
        KPITypeDTO smsNotifications = new KPITypeDTO("SMS_NOTIFICATIONS", totalSmsNotifications);
        KPITypeDTO pushNotifications = new KPITypeDTO("PUSH_NOTIFICATIONS", totalPushNotifications);

        kpisDTO.setUsers(users);
        kpisDTO.setEmailNotifications(emailNotifications);
        kpisDTO.setSmsNotifications(smsNotifications);
        kpisDTO.setPushNotifications(pushNotifications);

        return kpisDTO;
    }
}
