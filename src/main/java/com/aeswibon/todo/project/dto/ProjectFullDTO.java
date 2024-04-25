package com.aeswibon.todo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFullDTO {
    String uuid;
    String title;
    String createdAt;
    String updatedAt;
}
