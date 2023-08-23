package com.jjuarez.gila.service.impl;

import com.jjuarez.gila.entity.Topic;
import com.jjuarez.gila.exception.TopicNotFoundException;
import com.jjuarez.gila.repository.TopicRepository;
import com.jjuarez.gila.service.TopicService;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    @Override
    public Topic findByName(String name) {
        return topicRepository.findByName(name).orElseThrow(() ->
                new TopicNotFoundException("Topic not found: {}" + name));
    }
}
