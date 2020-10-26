package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.payload.ApiResponse;
import com.taskmanager.security.CurrentUser;
import com.taskmanager.security.UserPrincipal;
import com.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/task",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<ApiResponse> add(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity<ApiResponse> delete(@RequestBody Task task) {
        return taskService.delete(task);
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity<ApiResponse> update(@Valid @RequestBody Task task) {
        return taskService.update(task);
    }
}
