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
public class TodoDTO {
    String uuid;
    String description;
    boolean status;
    String projectTitle;
    String createdBy;
    String updatedBy;
}
