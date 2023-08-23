package com.jjuarez.gila.controller.advice;

import com.jjuarez.gila.exception.BroadcastChannelNotFoundException;
import com.jjuarez.gila.exception.TopicNotFoundException;
import com.jjuarez.gila.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @Test
    void testHandleTopicNotFoundException() {
        /* FIX THIS TEST -> TopicNotFoundException exception = new TopicNotFoundException("Topic not found");
        ApiResponse expectedResponse = new ApiResponse("Topic not found", "Error");

        ResponseEntity<ApiResponse> actualResponseEntity = customExceptionHandler.handleTopicNotFoundException(exception);

        assertEquals(expectedResponse.getMessage(), Objects.requireNonNull(actualResponseEntity.getBody()).getMessage());
        assertEquals(expectedResponse.getStatus(), actualResponseEntity.getBody().getStatus());

        assertEquals(HttpStatus.NOT_FOUND, actualResponseEntity.getStatusCode());*/
    }

    @Test
    void testHandleBroadcastChannelNotFoundException() {
        /* FIX THIS TEST -> BroadcastChannelNotFoundException exception = new BroadcastChannelNotFoundException("Broadcast channel not found");
        ApiResponse expectedResponse = new ApiResponse("Broadcast channel not found", "Error");

        ResponseEntity<ApiResponse> actualResponseEntity = customExceptionHandler.handleBroadcastChannelNotFoundException(exception);

        assertEquals(expectedResponse.getMessage(), Objects.requireNonNull(actualResponseEntity.getBody()).getMessage());
        assertEquals(expectedResponse.getStatus(), actualResponseEntity.getBody().getStatus());

        assertEquals(HttpStatus.NOT_FOUND, actualResponseEntity.getStatusCode());*/
    }
}