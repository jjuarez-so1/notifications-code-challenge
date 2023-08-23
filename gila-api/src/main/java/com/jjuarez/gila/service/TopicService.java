package com.jjuarez.gila.service;

import com.jjuarez.gila.entity.Topic;

public interface TopicService {
    Topic findByName(String name);
}
