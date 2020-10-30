package com.taskmanager.service;

import com.taskmanager.model.Project;
import com.taskmanager.model.Task;
import com.taskmanager.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<ApiResponse> save(Task task);

    ResponseEntity<ApiResponse> delete(Task task);

    ResponseEntity<ApiResponse> deleteMultiple(Project project);

    ResponseEntity<ApiResponse> update(Task task);
}
