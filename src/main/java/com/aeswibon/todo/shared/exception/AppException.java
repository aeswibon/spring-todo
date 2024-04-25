package com.aeswibon.todo.shared.exception;

import com.aeswibon.todo.shared.error.IError;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends Exception {
    IError error;
    Throwable rootException;
}
