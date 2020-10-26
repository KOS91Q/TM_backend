package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.payload.ApiResponse;
import com.taskmanager.payload.ResponseStatus;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<ApiResponse> save(Task task) {
        return new ResponseEntity<>(
                new ApiResponse(
                        ResponseStatus.success,
                        taskRepository.save(task),
                        "Task " + task.getName() + " created"
                ),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Task task) {
        Task deleteTask = taskRepository.findById(task.getId()).orElse(null);
        if (deleteTask == null) {
            return new ResponseEntity<>(
                    new ApiResponse(ResponseStatus.warn, task, "Task not found"),
                    HttpStatus.OK
            );
        }
        taskRepository.deleteById(task.getId());
        return new ResponseEntity<>(
                new ApiResponse(ResponseStatus.info, task, "Task " + deleteTask.getName() + " deleted"),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<ApiResponse> update(Task task) {
        Task updTask = taskRepository.findById(task.getId()).orElse(null);
        if (updTask == null) {
            return new ResponseEntity<>(
                    new ApiResponse(ResponseStatus.warn, task, "Task not found"),
                    HttpStatus.OK
            );
        }
        if (task.getName() != null && !task.getName().isEmpty()) {
            updTask.setName(task.getName());
        }
        if (task.getStatus() != null) {
            updTask.setStatus(task.getStatus());
        }
        if (task.getProjectId() != null && !updTask.getProjectId().equals(task.getProjectId())) {
            updTask.setProjectId(task.getProjectId());
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        ResponseStatus.info,
                        taskRepository.save(updTask),
                        "Task " + task.getName() + " updated"
                ),
                HttpStatus.OK
        );
    }
}
