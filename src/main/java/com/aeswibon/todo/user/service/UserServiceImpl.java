package com.aeswibon.todo.user.service;

import com.aeswibon.todo.shared.adapter.AppExceptionAdapter;
import com.aeswibon.todo.shared.constants.ErrorConstants;
import com.aeswibon.todo.shared.exception.AppException;
import com.aeswibon.todo.user.db.entity.User;
import com.aeswibon.todo.user.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User findUser() throws AppException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((DefaultOAuth2User) auth.getPrincipal()).getAttributes().get("email").toString();
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw AppExceptionAdapter.getExceptionObjectFrom(ErrorConstants.RESOURCE_NOT_FOUND);
        }
        return user.get();
    }
}
