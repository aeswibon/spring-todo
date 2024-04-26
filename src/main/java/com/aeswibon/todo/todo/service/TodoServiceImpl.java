package com.aeswibon.todo.todo.service;

import com.aeswibon.todo.shared.dto.PaginationFilterSortDTO;
import com.aeswibon.todo.shared.utils.PagingHelper;
import com.aeswibon.todo.todo.adapter.TodoAdapter;
import com.aeswibon.todo.shared.constants.Constants;
import com.aeswibon.todo.shared.constants.ErrorConstants;
import com.aeswibon.todo.todo.db.entity.Todo;
import com.aeswibon.todo.todo.db.repository.TodoRepository;
import com.aeswibon.todo.todo.dto.*;
import com.aeswibon.todo.shared.adapter.AppExceptionAdapter;
import com.aeswibon.todo.shared.dto.MessageResponseDTO;
import com.aeswibon.todo.shared.exception.AppException;
import com.aeswibon.todo.user.db.entity.User;
import com.aeswibon.todo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepo;
    private UserService userService;

    private Todo _findTodo(String todoId) throws AppException {
        Optional<Todo> todo = todoRepo.findByUuidAndProject_User(UUID.fromString(todoId), userService.findUser());
        if (todo.isEmpty()) {
            throw AppExceptionAdapter.getExceptionObjectFrom(ErrorConstants.RESOURCE_NOT_FOUND);
        }
        return todo.get();
    }

    @Override
    public MessageResponseDTO createTodo(TodoCreateDTO request) throws AppException {
        Optional<Todo> todo = todoRepo.findByDescriptionAndProject_User(request.getDescription(), userService.findUser());
        if (todo.isPresent()) {
            throw AppExceptionAdapter.getExceptionObjectFrom(ErrorConstants.RESOURCE_ALREADY_EXISTS);
        }
        Todo newTodo = Todo.builder()
                .description(request.getDescription())
                .status(request.isStatus())
                .build();
        todoRepo.save(newTodo);
        return MessageResponseDTO.builder().message(Constants.RESOURCE_CREATED_SUCCESS_MESSAGE).build();
    }

    @Override
    public MessageResponseDTO updateTodo(String todoId, TodoUpdateDTO request) throws AppException {
        Todo existingTodo = _findTodo(todoId);
        existingTodo.setDescription(request.getDescription());
        existingTodo.setStatus(request.isStatus());
        todoRepo.save(existingTodo);
        return MessageResponseDTO.builder().message(Constants.RESOURCE_UPDATED_SUCCESS_MESSAGE).build();
    }

    @Override
    public MessageResponseDTO deleteTodo(String todoId) throws AppException {
        Todo existingTodo = _findTodo(todoId);
        todoRepo.delete(existingTodo);
        return MessageResponseDTO.builder().message(Constants.RESOURCE_DELETED_SUCCESS_MESSAGE).build();
    }

    @Override
    public TodosResponseDTO findAllTodos(PaginationFilterSortDTO data) throws AppException {
        Pageable paging = PagingHelper.getPagingObject(data.getPageNumber(), data.getPageSize(), data.getSortBy());
        Page<Todo> query = todoRepo.findAllByProject_User(userService.findUser(), paging);
        List<TodoDTO> todos = new ArrayList<>();
        query.forEach(todo -> todos.add(TodoAdapter.getTodo(todo)));
        return TodosResponseDTO.builder().count((long) todos.size()).todos(todos).build();
    }

    @Override
    public TodoFullDTO findTodo(String todoId) throws AppException {
        Todo todo = _findTodo(todoId);
        return TodoAdapter.getCompleteTodo(todo);
    }
}
