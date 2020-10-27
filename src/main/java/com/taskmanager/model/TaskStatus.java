package com.taskmanager.model;


import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    NEW("NEW"),
    IN_PROGRESS("IN PROGRESS"),
    COMPLETE("COMPLETED");

    @JsonValue
    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String get() {
        return status;
    }
}