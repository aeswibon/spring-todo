package com.aeswibon.todo.todo.db.entity;

import com.aeswibon.todo.project.db.entity.Project;
import com.aeswibon.todo.shared.entity.IEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
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

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
