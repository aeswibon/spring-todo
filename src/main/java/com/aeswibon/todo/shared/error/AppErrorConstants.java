package com.aeswibon.todo.shared.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorConstants implements IError {

    INTERNAL_SERVER_ERROR(
            "blog",
            "internal_server_error",
            "Something went wrong. Please try again later",
            HttpStatus.INTERNAL_SERVER_ERROR.value()
    );

    private final String entity;
    private final String type;
    private final String reason;
    private final int httpStatusCode;
}
