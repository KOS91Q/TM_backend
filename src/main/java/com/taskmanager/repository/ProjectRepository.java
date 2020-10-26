package com.taskmanager.repository;

import com.taskmanager.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface ProjectRepository extends CrudRepository<Project, Long> {
    Collection<Project> findAllByUserId(Long userId);
}
