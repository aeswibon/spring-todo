package com.aeswibon.todo.shared.constants;

import com.aeswibon.todo.shared.error.IError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorConstants implements IError {
    UNAUTHORIZED(
            "auth",
            "unauthorized",
            "You are not authorized to access this resource",
            HttpStatus.UNAUTHORIZED.value()
    ),

    RESOURCE_NOT_FOUND(
            "resource",
            "invalid_uuid",
            "uuid is invalid",
            HttpStatus.NOT_FOUND.value()
    ),

    RESOURCE_ALREADY_EXISTS(
      "resource",
      "duplicate_description",
      "Description is duplicate",
      HttpStatus.INTERNAL_SERVER_ERROR.value()
    ),

    RESOURCE_UPDATE_FAILED(
            "resource",
            "field_update",
            "Updating the resource failed",
            HttpStatus.INTERNAL_SERVER_ERROR.value()
    ),

    RESOURCE_FETCH_PRIVATE_FAILED(
            "resource",
            "fetch",
            "You don't have permission to access private resource",
            HttpStatus.FORBIDDEN.value()
    );

    private final String entity;
    private final String type;
    private final String reason;
    private final int httpStatusCode;
}
