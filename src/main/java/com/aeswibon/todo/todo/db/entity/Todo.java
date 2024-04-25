package com.aeswibon.todo.todo.db.entity;

import com.aeswibon.todo.project.db.entity.Project;
import com.aeswibon.todo.shared.entity.IEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(
        name = "todo",
        indexes = {
            @Index(name = "uuidStatusIndex", columnList = "uuid, status"),
        }
)
public class Todo implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String description;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_uuid")
    @ToString.Exclude
    private Project project;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column(nullable = false)
    private ZonedDateTime updatedAt;

}
