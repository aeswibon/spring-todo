package com.aeswibon.todo.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoFullDTO {
    String uuid;
    String description;
    String projectTitle;
    boolean status;
    String createdAt;
    String updatedAt;
}
