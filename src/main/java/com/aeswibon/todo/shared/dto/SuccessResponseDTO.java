package com.aeswibon.todo.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseDTO<T> implements IDTO {
    private String entity;
    private T data;
}
