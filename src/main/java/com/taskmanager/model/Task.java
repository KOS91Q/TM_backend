package com.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskmanager.helper.Views;
import com.taskmanager.payload.DTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Task implements DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(Views.Hide.class)
    @Column(name = "project_id")
    private Long projectId;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;
}
