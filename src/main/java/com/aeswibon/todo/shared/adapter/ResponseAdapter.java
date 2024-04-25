package com.aeswibon.todo.shared.adapter;

import com.aeswibon.todo.shared.dto.ErrorDTO;
import com.aeswibon.todo.shared.dto.ErrorResponseDTO;
import com.aeswibon.todo.shared.dto.SuccessResponseDTO;

public class ResponseAdapter {
    public static <T> SuccessResponseDTO<T> createSuccessDTO(String entity, T data) {
        return new SuccessResponseDTO<T>(entity, data);
    }

    public static ErrorResponseDTO createFailureDTO(String entity, ErrorDTO errorDTO) {
        return new ErrorResponseDTO(entity, errorDTO);
    }
}
