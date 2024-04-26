package com.aeswibon.todo.todo.controller;

import com.aeswibon.todo.shared.dto.PaginationFilterSortDTO;
import com.aeswibon.todo.todo.dto.TodoCreateDTO;
import com.aeswibon.todo.todo.dto.TodoFullDTO;
import com.aeswibon.todo.todo.dto.TodoUpdateDTO;
import com.aeswibon.todo.todo.dto.TodosResponseDTO;
import com.aeswibon.todo.todo.service.TodoService;
import com.aeswibon.todo.shared.dto.MessageResponseDTO;
import com.aeswibon.todo.shared.dto.SuccessResponseDTO;
import com.aeswibon.todo.shared.exception.AppException;
import com.aeswibon.todo.shared.utils.ResponseHelper;
import com.aeswibon.todo.shared.utils.Routes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Routes.Todo)
public class TodoController {

    private TodoService todoService;
    private ResponseHelper responseHelper;

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<TodosResponseDTO>> findAllTodos(
            @RequestParam PaginationFilterSortDTO paginationFilterSortDTO
    ) throws AppException {
        TodosResponseDTO result = todoService.findAllTodos(paginationFilterSortDTO);
        return responseHelper.getSuccessResponse("todo", result);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<SuccessResponseDTO<TodoFullDTO>> findTodo(
            @PathVariable("uuid") String todoUuid
    ) throws AppException {
        TodoFullDTO result = todoService.findTodo(todoUuid);
        return responseHelper.getSuccessResponse("todo", result);
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDTO<MessageResponseDTO>> createTodo(
            @RequestBody TodoCreateDTO request
    ) throws AppException {
        MessageResponseDTO result = todoService.createTodo(request);
        return responseHelper.getSuccessResponse("todo", result);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<SuccessResponseDTO<MessageResponseDTO>> updateTodo(
            @PathVariable("uuid") String todoUuid,
            @RequestBody TodoUpdateDTO request
    ) throws AppException {
        MessageResponseDTO result = todoService.updateTodo(todoUuid, request);
        return responseHelper.getSuccessResponse("todo", result);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<SuccessResponseDTO<MessageResponseDTO>> deleteTodo(
            @PathVariable("uuid") String todoUuid
    ) throws AppException {
        MessageResponseDTO result = todoService.deleteTodo(todoUuid);
        return responseHelper.getSuccessResponse("todo", result);
    }

}