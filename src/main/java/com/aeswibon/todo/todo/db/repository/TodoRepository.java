package com.aeswibon.todo.todo.db.repository;

import com.aeswibon.todo.project.db.entity.Project;
import com.aeswibon.todo.todo.db.entity.Todo;
import com.aeswibon.todo.user.db.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
    Optional<Todo> findByDescriptionAndProject_User(String description, User user);
    Optional<Todo> findByUuidAndProject_User(UUID uuid, User user);
    Page<Todo> findAllByProject_User(User user, Pageable pageable);
    List<Todo> findAllByProject(Project project);
}
