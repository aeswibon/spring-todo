package com.aeswibon.todo.todo.adapter;

import com.aeswibon.todo.todo.db.entity.Todo;
import com.aeswibon.todo.todo.dto.TodoDTO;
import com.aeswibon.todo.todo.dto.TodoFullDTO;

public class TodoAdapter {
    public static TodoDTO getTodo(Todo todo) {
        return TodoDTO
                .builder()
                .uuid(todo.getUuid().toString())
                .description(todo.getDescription())
                .projectTitle(todo.getProject().getTitle())
                .status(todo.isStatus())
                .build();
    }

    public static TodoFullDTO getCompleteTodo(Todo todo) {
        return TodoFullDTO
                .builder()
                .uuid(todo.getUuid().toString())
                .description(todo.getDescription())
                .projectTitle(todo.getProject().getTitle())
                .status(todo.isStatus())
                .createdAt(todo.getCreatedAt().toString())
                .updatedAt(todo.getUpdatedAt().toString())
                .build();
    }
}
