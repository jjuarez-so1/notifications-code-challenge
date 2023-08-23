package com.jjuarez.gila.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jjuarez.gila.constants.ApiConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String category;
    private String message;
    private Instant startTime;
    private Instant endTime;
    private String status;

   @OneToMany(mappedBy = "notification", fetch = FetchType.EAGER)
   @JsonIgnore
   private List<SentNotification> sentNotifications = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<SentNotification> getSentNotifications() {
        return sentNotifications;
    }

    public void addSentNotification(SentNotification sentNotification) {
        sentNotifications.add(sentNotification);
        sentNotification.setNotification(this);
    }

    /*public static Notification from(NotificationRequest notificationRequest) {
        return new Notification.Builder()
                .withCategory(notificationRequest.category())
                .withMessage(notificationRequest.message())
                .build();
    }*/

    public static Notification from(final String message, final Topic topic) {
        return new Notification.Builder()
                .withTopic(topic)
                .withMessage(message)
                .build();
    }

    public static class Builder {
        private final Notification notification;

        public Builder() {
            notification = new Notification();
            notification.setStatus(ApiConstants.STATUS_IN_PROGRESS);
            notification.setStartTime(Instant.now());
        }

        public Builder withCategory(final String category) {
            notification.setCategory(category);
            return this;
        }

        public Builder withMessage(final String message) {
            notification.setMessage(message);
            return this;
        }

        public Notification build() {
            return notification;
        }

        public Builder withTopic(final Topic topic) {
            notification.setTopic(topic);
            return this;
        }
    }
}
