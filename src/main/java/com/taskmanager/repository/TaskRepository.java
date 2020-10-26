package com.taskmanager.repository;

import com.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Task, Long> {

}
