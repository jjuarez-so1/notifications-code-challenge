package com.jjuarez.gila.dto;

import java.util.List;

public class PaginatedMessageDTO {
    private List<MessageDTO> messages;
    private long totalCount;

    public PaginatedMessageDTO(List<MessageDTO> messages, long totalCount) {
        this.messages = messages;
        this.totalCount = totalCount;
    }

    // Getters and setters

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}