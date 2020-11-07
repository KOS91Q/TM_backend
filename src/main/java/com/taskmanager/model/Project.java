package com.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskmanager.helper.Views;
import com.taskmanager.payload.DTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Project implements DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "project_id")
    @OrderBy("pos")
    private List<Task> tasks = new ArrayList<>();

    @JsonView(Views.Hide.class)
    @Column(name = "user_id")
    private Long userId;
}
