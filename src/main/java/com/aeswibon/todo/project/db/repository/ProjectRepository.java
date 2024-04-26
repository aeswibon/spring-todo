package com.aeswibon.todo.project.db.repository;

import com.aeswibon.todo.project.db.entity.Project;
import com.aeswibon.todo.user.db.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Optional<Project> findByTitle(String title);
    Optional<Project> findByTitleAndUser(String title, User user);
    Optional<Project> findByUuidAndUser(UUID uuid, User user);
    Page<Project> findAllByUser(User user, Pageable pageable);
}
