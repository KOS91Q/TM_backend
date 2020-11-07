package com.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskmanager.helper.Views;
import com.taskmanager.payload.DTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    @JsonView(Views.ProjectId.class)
    @Column(name = "project_id")
    private Long projectId;

    private String name;

    private LocalDate deadline = null;

    private Integer pos;

    @Transient
    @JsonView(Views.Hide.class)
    private String oldName;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
