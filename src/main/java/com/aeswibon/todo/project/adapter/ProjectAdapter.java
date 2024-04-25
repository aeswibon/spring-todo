package com.aeswibon.todo.project.adapter;

import com.aeswibon.todo.project.db.entity.Project;
import com.aeswibon.todo.project.dto.ProjectDTO;
import com.aeswibon.todo.project.dto.ProjectFullDTO;

public class ProjectAdapter {
    public static ProjectDTO getProject(Project project) {
        return ProjectDTO
                .builder()
                .uuid(project.getUuid().toString())
                .title(project.getTitle())
                .build();
    }

    public static ProjectFullDTO getCompleteProject(Project project) {
        return ProjectFullDTO
                .builder()
                .uuid(project.getUuid().toString())
                .title(project.getTitle())
                .createdAt(project.getCreatedAt().toString())
                .updatedAt(project.getUpdatedAt().toString())
                .build();
    }
}
