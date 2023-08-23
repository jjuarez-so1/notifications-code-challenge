package com.jjuarez.gila.controller.advice;

import com.jjuarez.gila.constants.ApiConstants;
import com.jjuarez.gila.exception.BroadcastChannelNotFoundException;
import com.jjuarez.gila.exception.TopicNotFoundException;
import com.jjuarez.gila.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleTopicNotFoundException(final TopicNotFoundException ex) {
        return new ApiResponse(ex.getMessage(), ApiConstants.STATUS_ERROR);
    }

    @ExceptionHandler(BroadcastChannelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleBroadcastChannelNotFoundException(final BroadcastChannelNotFoundException ex) {
        return new ApiResponse(ex.getMessage(), ApiConstants.STATUS_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return new ApiResponse(ex.getMessage(), ApiConstants.STATUS_ERROR);
    }
}
