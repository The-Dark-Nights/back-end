package com.darknights.devigation.global.common.response.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class CustomApiResponse<T> {
    public int status;
    public String message;
    public LocalDateTime timestamp = LocalDateTime.now();
    private T data;
    public CustomApiResponse() {}

    public CustomApiResponse(int status, String message, T data){
        this.status=status;
        this.message=message;
        this.data=data;
    }

    public CustomApiResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public CustomApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomApiResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
