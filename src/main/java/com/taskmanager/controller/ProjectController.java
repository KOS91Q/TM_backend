package com.taskmanager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskmanager.helper.Views;
import com.taskmanager.model.Project;
import com.taskmanager.payload.ApiResponse;
import com.taskmanager.security.CurrentUser;
import com.taskmanager.security.UserPrincipal;
import com.taskmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        path = "/project",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public @ResponseBody
    @JsonView(Views.Public.class)
    ResponseEntity<ApiResponse> add(@Valid @RequestBody Project project, @CurrentUser UserPrincipal user) {
        project.setUserId(user.getId());
        return projectService.save(project);
    }

    @PutMapping
    public @ResponseBody
    @JsonView(Views.Public.class)
    ResponseEntity<ApiResponse> update(@RequestBody Project project, @CurrentUser UserPrincipal user) {
        return projectService.update(project);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public @ResponseBody
    ResponseEntity<Iterable<Project>> getAll(@CurrentUser UserPrincipal user) {
        return new ResponseEntity<>(projectService.findAllByUserId(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity<ApiResponse> delete(@RequestBody Project project, @CurrentUser UserPrincipal user) {
        project.setUserId(user.getId());
        return projectService.delete(project);
    }
}
