package com.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"email", "provider"}
                )
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Transient
    @JsonIgnore
    private Boolean emailVerified = false;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @OrderBy("id")
    private List<Project> projects = new ArrayList<>();

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String imageUrl;
    private String providerId;
}
