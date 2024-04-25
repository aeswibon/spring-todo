package com.aeswibon.todo.todo.service;

import com.aeswibon.todo.shared.dto.PaginationFilterSortDTO;
import com.aeswibon.todo.todo.dto.TodoCreateDTO;
import com.aeswibon.todo.todo.dto.TodoFullDTO;
import com.aeswibon.todo.todo.dto.TodoUpdateDTO;
import com.aeswibon.todo.todo.dto.TodosResponseDTO;
import com.aeswibon.todo.shared.dto.MessageResponseDTO;
import com.aeswibon.todo.shared.exception.AppException;

public interface TodoService {
    MessageResponseDTO createTodo(TodoCreateDTO request) throws AppException;
    MessageResponseDTO updateTodo(String todoId, TodoUpdateDTO request) throws AppException;
    MessageResponseDTO deleteTodo(String todoId) throws AppException;
    TodosResponseDTO findAllTodos(PaginationFilterSortDTO data) throws AppException;
    TodoFullDTO findTodo(String todoId) throws AppException;
}
