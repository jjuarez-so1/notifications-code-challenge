package com.jjuarez.gila.service;

import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<User> getUsersBySubscribedTopic(Topic topic);
}
