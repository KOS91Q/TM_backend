package com.taskmanager.repository;

import com.taskmanager.model.AuthProvider;
import com.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndProvider(@Email String email, @NotNull AuthProvider provider);

    Boolean existsByEmailAndProvider(@Email String email, @NotNull AuthProvider provider);

}
