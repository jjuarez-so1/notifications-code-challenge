package com.jjuarez.gila.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscribed_topics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> subscribedTopics;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_preferred_channels",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private List<BroadcastChannel> preferredChannels;

    public User() {
    }

    public User(Long id, String name, String email, String phone,
                List<Topic> subscribedTopics, List<BroadcastChannel> preferredChannels) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscribedTopics = subscribedTopics;
        this.preferredChannels = preferredChannels;
    }

    public User(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Topic> getSubscribedTopics() {
        return subscribedTopics;
    }

    public List<BroadcastChannel> getPreferredChannels() {
        return preferredChannels;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSubscribedTopics(List<Topic> subscribedTopics) {
        this.subscribedTopics = subscribedTopics;
    }

    public void setPreferredChannels(List<BroadcastChannel> preferredChannels) {
        this.preferredChannels = preferredChannels;
    }

    public boolean isSubscribedTo(Topic topic) {
        return subscribedTopics.contains(topic);
    }

    public boolean prefersChannel(BroadcastChannel channel) {
        return preferredChannels.contains(channel);
    }

    public static class Builder {
        private final User user;
        public Builder() {
            user = new User();
        }
        public Builder name(final String name) {
            user.setName(name);
            return this;
        }
        public Builder email(final String email) {
            user.setEmail(email);
            return this;
        }

        public Builder phone(final String phone) {
            user.setPhone(phone);
            return this;
        }

        public Builder subscribedTopics(final List<Topic> subscribedTopics) {
            user.setSubscribedTopics(subscribedTopics);
            return this;
        }

        public Builder preferredChannels(final List<BroadcastChannel> preferredChannels) {
            user.setPreferredChannels(preferredChannels);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
