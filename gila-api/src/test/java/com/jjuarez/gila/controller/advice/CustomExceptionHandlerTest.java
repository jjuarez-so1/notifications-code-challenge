package com.jjuarez.gila.controller.advice;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.exception.BroadcastChannelNotFoundException;
import com.jjuarez.gila.exception.TopicNotFoundException;
import com.jjuarez.gila.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    private TopicNotFoundException topicNotFoundException;
    private BroadcastChannelNotFoundException broadcastChannelNotFoundException;
    private MethodArgumentNotValidException methodArgumentNotValidException;

    private static final String TOPIC_NOT_FOUND_MESSAGE = "Topic not found";
    private static final String BROADCAST_CHANNEL_NOT_FOUND_MESSAGE = "Broadcast channel not found";

    @BeforeEach
    void setUp() {
        topicNotFoundException = new TopicNotFoundException(TOPIC_NOT_FOUND_MESSAGE);
        broadcastChannelNotFoundException = new BroadcastChannelNotFoundException(BROADCAST_CHANNEL_NOT_FOUND_MESSAGE);
        methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
    }

    @Test
    void testHandleTopicNotFoundException() {
        final ApiResponse apiResponse = customExceptionHandler.handleTopicNotFoundException(topicNotFoundException);
        assertEquals(TOPIC_NOT_FOUND_MESSAGE, apiResponse.getMessage());
        assertEquals(ApiConstants.STATUS_ERROR, apiResponse.getStatus());
    }

    @Test
    void testHandleBroadcastChannelNotFoundException() {
        final ApiResponse apiResponse = customExceptionHandler
                .handleBroadcastChannelNotFoundException(broadcastChannelNotFoundException);

        assertEquals(BROADCAST_CHANNEL_NOT_FOUND_MESSAGE, apiResponse.getMessage());
        assertEquals(ApiConstants.STATUS_ERROR, apiResponse.getStatus());
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        final ApiResponse apiResponse = customExceptionHandler
                .handleMethodArgumentNotValidException(methodArgumentNotValidException);
        assertEquals(ApiConstants.STATUS_ERROR, apiResponse.getStatus());
    }
}
