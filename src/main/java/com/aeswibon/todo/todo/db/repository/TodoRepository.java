package com.aeswibon.todo.todo.db.repository;

import com.aeswibon.todo.todo.db.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {

    Optional<Todo> findByDescription(String description);
}
