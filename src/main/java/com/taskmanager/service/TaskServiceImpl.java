package com.taskmanager.service;

import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.Project;
import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.payload.ApiResponse;
import com.taskmanager.payload.ResponseStatus;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseEntity<ApiResponse> save(Task task) {
        task.setStatus(TaskStatus.NEW);
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
                    new ApiResponse(ResponseStatus.warning, task, "Task not found"),
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
    public ResponseEntity<ApiResponse> deleteMultiple(Project project) {
        taskRepository.deleteAll(project.getTasks());
        return new ResponseEntity<>(
                new ApiResponse(ResponseStatus.info, project, "deleted " + project.getTasks().size() + " Tasks"),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<ApiResponse> update(Task task) {
        return new ResponseEntity<>(updateResponse(task), HttpStatus.OK);
    }

    private ApiResponse updateResponse(Task task) {
        Task updTask = taskRepository.findById(task.getId()).orElse(null);
        if (updTask == null) {
            return new ApiResponse(ResponseStatus.warning, task, "Task not found");
        } else if (task.getName() != null && !task.getName().isEmpty()) {
            updTask.setName(task.getName());
            return new ApiResponse(
                    ResponseStatus.info,
                    taskRepository.save(updTask),
                    "Task " + task.getOldName() + " renamed to " + updTask.getName()
            );
        } else if (task.getStatus() != null) {
            updTask.setStatus(task.getStatus());
            return new ApiResponse(
                    ResponseStatus.info,
                    taskRepository.save(updTask),
                    "Task " + updTask.getName() + " status updated to " + updTask.getStatus().get()
            );
        } else if (task.getDeadline() != null) {
            updTask.setDeadline(task.getDeadline());
            return new ApiResponse(
                    ResponseStatus.info,
                    taskRepository.save(updTask),
                    "Task " + updTask.getName() + " deadline updated to " + updTask.getDeadline()
            );
        } else if (task.getProjectId() != null && !updTask.getProjectId().equals(task.getProjectId())) {
            updTask.setProjectId(task.getProjectId());
            updTask.setPos(task.getPos());
            return new ApiResponse(
                    ResponseStatus.info,
                    taskRepository.save(updTask),
                    "Task " + updTask.getName() + " moved to " +
                            projectRepository.findById(task.getProjectId())
                                    .orElseThrow(() -> new ResourceNotFoundException("Project", "id", task.getProjectId()))
                                    .getName() + " project"
            );
        } else if (task.getPos() != null && !updTask.getPos().equals(task.getPos())) {
            updTask.setPos(task.getPos());
            return new ApiResponse(
                    ResponseStatus.info,
                    taskRepository.save(updTask),
                    "Task " + updTask.getName() + " changed position"
            );
        } else {
            return new ApiResponse(
                    ResponseStatus.warning,
                    taskRepository.save(updTask),
                    "Task " + updTask.getName() + " has not changed"
            );
        }

    }
}
