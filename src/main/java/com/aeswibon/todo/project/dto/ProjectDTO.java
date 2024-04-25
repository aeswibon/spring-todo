package com.aeswibon.todo.project.dto;

import com.aeswibon.todo.todo.db.entity.Todo;
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
    List<Todo> todos;
    String createdBy;
    String updatedBy;
}
