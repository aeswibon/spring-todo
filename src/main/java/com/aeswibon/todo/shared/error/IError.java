package com.aeswibon.todo.shared.error;

public interface IError {
    String getEntity();
    String getType();
    String getReason();
    int getHttpStatusCode();
}
