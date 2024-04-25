package com.aeswibon.todo.shared.adapter;

import com.aeswibon.todo.shared.error.IError;
import com.aeswibon.todo.shared.exception.AppException;

public class AppExceptionAdapter {
    public static AppException getExceptionObjectFrom(IError error) {
        return AppException
                .builder()
                .error(error)
                .build();
    }
}
