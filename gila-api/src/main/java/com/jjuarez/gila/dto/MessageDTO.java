package com.jjuarez.gila.dto;

import java.time.Instant;

public class MessageDTO {
    private String channel;
    private String userEmail;

    private Instant sentTime;

    private Long userId;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Instant getSentTime() {
        return sentTime;
    }

    public void setSentTime(Instant sentTime) {
        this.sentTime = sentTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
