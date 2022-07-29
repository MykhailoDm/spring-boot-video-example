package com.videostreamer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VideoAlreadyExistsException extends RuntimeException {
    public VideoAlreadyExistsException(String message) {
        super(message);
    }
}
