package com.aeswibon.todo.shared.utils;

import com.aeswibon.todo.shared.adapter.ResponseAdapter;
import com.aeswibon.todo.shared.dto.SuccessResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper {
    public <T> ResponseEntity<SuccessResponseDTO<T>> getSuccessResponse(String entity, T data) {
        return ResponseEntity.ok().body(
                ResponseAdapter.createSuccessDTO(
                        entity
                        , data
                )
        );
    }

    public <T> ResponseEntity<T> getSuccessResponse(T data) {
        return ResponseEntity.ok().body(data);
    }
}
