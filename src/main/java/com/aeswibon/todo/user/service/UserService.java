package com.aeswibon.todo.user.service;

import com.aeswibon.todo.shared.exception.AppException;
import com.aeswibon.todo.user.db.entity.User;

public interface UserService {
    public User findUser() throws AppException;
}
