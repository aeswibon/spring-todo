package com.aeswibon.todo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    String uuid;
    String title;
    Integer pending;
    Integer completed;
    String createdBy;
    String updatedBy;
}
