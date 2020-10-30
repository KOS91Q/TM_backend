package com.taskmanager.service;

import com.taskmanager.model.Project;
import com.taskmanager.payload.ApiResponse;
import com.taskmanager.payload.ResponseStatus;
import com.taskmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseEntity<ApiResponse> save(Project project) {
        return new ResponseEntity<>(
                new ApiResponse(
                        ResponseStatus.success,
                        projectRepository.save(project),
                        "Project " + project.getName() + " created"
                ),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<ApiResponse> update(Project project) {
        Project updProject = projectRepository.findById(project.getId()).get();
        if (project.getName() != null && !project.getName().isEmpty()) {
            updProject.setName(project.getName());
            return new ResponseEntity<>(
                    new ApiResponse(
                            ResponseStatus.info,
                            projectRepository.save(updProject),
                            "Project " + project.getName() + " updated"
                    ),
                    HttpStatus.CREATED
            );
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(ResponseStatus.warning, project, "Project not updated"),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public Iterable<Project> findAllByUserId(Long userId) {
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Project project) {
        Project deleteProject = projectRepository.findById(project.getId()).orElse(null);
        if (deleteProject == null){
            return new ResponseEntity<>(
                    new ApiResponse(ResponseStatus.warning, project, "Project not found"),
                    HttpStatus.OK
            );
        }
        projectRepository.deleteById(project.getId());
        return new ResponseEntity<>(
                new ApiResponse(
                        ResponseStatus.info,
                        deleteProject,
                        "Project " + deleteProject.getName() + " deleted"),
                HttpStatus.OK
        );
    }
}
