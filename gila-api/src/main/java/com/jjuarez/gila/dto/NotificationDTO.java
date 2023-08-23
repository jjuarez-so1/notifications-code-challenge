package com.jjuarez.gila.dto;

import com.jjuarez.gila.entity.Notification;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationDTO {
    private Long id;
    private String topic;
    private String message;
    private Instant startTime;
    private Instant endTime;
    private String status;

    public static NotificationDTO from(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setTopic(notification.getTopic().getName());
        dto.setMessage(notification.getMessage());
        dto.setStartTime(notification.getStartTime());
        dto.setEndTime(notification.getEndTime());
        dto.setStatus(notification.getStatus());
        return dto;
    }

    public static List<NotificationDTO> from(List<Notification> notifications) {
        return notifications.stream()
                .map(NotificationDTO::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}