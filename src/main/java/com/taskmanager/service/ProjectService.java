package com.taskmanager.service;

import com.taskmanager.model.Project;
import com.taskmanager.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<ApiResponse> save(Project project);

    ResponseEntity<ApiResponse> update(Project project);

    Iterable<Project> findAllByUserId(Long userId);

    ResponseEntity<ApiResponse> delete(Project project);
}
