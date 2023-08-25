package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.dto.KPITypeDTO;
import com.jjuarez.gila.dto.KPIsDTO;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.repository.NotificationRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public KpiServiceImpl(final NotificationRepository notificationRepository,
                          final UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public KPIsDTO getKpisForLastNotification() {
        LOG.info("Get kpis for last notification");
        final Notification lastNotification = notificationRepository.findTopByOrderByStartTimeDesc();

        if (lastNotification != null) {
            return calculateKPIs(lastNotification);
        }

        return new KPIsDTO();
    }

    private KPIsDTO calculateKPIs(final Notification notification) {
        final KPIsDTO kpisDTO = new KPIsDTO();

        kpisDTO.setInProgress(notification.getEndTime() == null);

        final long totalUsers = userRepository.count();

        final long totalEmailNotifications = notification.getSentNotifications().stream()
                .filter(sentNotification -> sentNotification.getChannel().getName()
                        .equalsIgnoreCase(ApiConstants.BROADCAST_CHANNEL_EMAIL))
                .count();

        final long totalSmsNotifications = notification.getSentNotifications().stream()
                .filter(sentNotification -> sentNotification.getChannel().getName()
                        .equalsIgnoreCase(ApiConstants.BROADCAST_CHANNEL_SMS))
                .count();

        final long totalPushNotifications = notification.getSentNotifications().stream()
                .filter(sentNotification -> sentNotification.getChannel().getName()
                        .equalsIgnoreCase(ApiConstants.BROADCAST_CHANNEL_PUSH_NOTIFICATIONS))
                .count();

        final KPITypeDTO users = new KPITypeDTO(ApiConstants.KPIS_USERS_TITLE, totalUsers);
        final KPITypeDTO emailNotifications = new KPITypeDTO(ApiConstants.KPIS_EMAIL_NOTIFICATIONS, totalEmailNotifications);
        final KPITypeDTO smsNotifications = new KPITypeDTO(ApiConstants.KPIS_SMS_NOTIFICATIONS, totalSmsNotifications);
        final KPITypeDTO pushNotifications = new KPITypeDTO(ApiConstants.KPIS_PUSH_NOTIFICATIONS, totalPushNotifications);

        kpisDTO.setUsers(users);
        kpisDTO.setEmailNotifications(emailNotifications);
        kpisDTO.setSmsNotifications(smsNotifications);
        kpisDTO.setPushNotifications(pushNotifications);

        return kpisDTO;
    }
}
