package com.taskmanager.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;

@Getter
@Setter
public class ApiResponse {
    private ResponseStatus status;
    private String message;
    private DTO data;

    public ApiResponse(ResponseStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(ResponseStatus status, DTO data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
