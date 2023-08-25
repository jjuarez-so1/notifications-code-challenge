package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.dto.NotificationDTO;
import com.jjuarez.gila.entity.Notification;
import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;
import com.jjuarez.gila.notification.NotificationStrategyContext;
import com.jjuarez.gila.repository.NotificationRepository;
import com.jjuarez.gila.request.NotificationRequest;
import com.jjuarez.gila.service.NotificationService;
import com.jjuarez.gila.service.TopicService;
import com.jjuarez.gila.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationStrategyContext notificationStrategyContext;
    private final TopicService topicService;
    private final UserService userService;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(final NotificationStrategyContext notificationStrategyContext,
                                   final TopicService topicService,
                                   final UserService userService,
                                   final NotificationRepository notificationRepository) {
        this.notificationStrategyContext = notificationStrategyContext;
        this.topicService = topicService;
        this.userService = userService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void broadcast(final NotificationRequest request) {
        LOG.info("Broadcasting request: {}", request);
        final Notification notification = Notification.from(request.message(),
                topicService.findByName(request.category()));
        notificationRepository.save(notification);

        final List<CompletableFuture<Void>> futures = userService.getAllUsers().stream()
                .filter(user -> isUserSubscribedToTopic(user, request.category()))
                .map(user -> CompletableFuture.runAsync(() -> sendNotification(user, notification)))
                .toList();

        final CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.thenRun(() -> updateNotificationStatus(notification)).join();
    }

    @Override
    public List<NotificationDTO> getLast5Notifications() {
        LOG.info("Fetching the las 5 notifications");
        final List<Notification> notifications = notificationRepository.findTop5ByOrderByStartTimeDesc();
        return NotificationDTO.from(notifications);
    }

    private boolean isUserSubscribedToTopic(final User user, final String category) {
        final Topic topic = topicService.findByName(category);
        return topic != null && user.isSubscribedTo(topic);
    }

    private void sendNotification(final User user,
                                  final Notification notification) {
        notificationStrategyContext.sendNotification(user, notification);
    }

    private void updateNotificationStatus(final Notification notification) {
        notification.setStatus(ApiConstants.STATUS_COMPLETED);
        notification.setEndTime(Instant.now());
        notificationRepository.save(notification);
    }
}
