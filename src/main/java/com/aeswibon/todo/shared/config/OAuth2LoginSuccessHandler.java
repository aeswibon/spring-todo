package com.aeswibon.todo.shared.config;

import com.aeswibon.todo.user.db.entity.User;
import com.aeswibon.todo.user.db.entity.UserRole;
import com.aeswibon.todo.user.db.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${app.oauth2.authorizedRedirectUri}")
    private String authorizedRedirectUri;

    private UserRepository userRepository;

    @Autowired
    public OAuth2LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void setAuthentication(User user, DefaultOAuth2User defaultOAuth2User) {
        DefaultOAuth2User updatedOAuth2User = new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority(user.getRole().name())),
                defaultOAuth2User.getAttributes(),
                "name"
        );
        Authentication auth = new OAuth2AuthenticationToken(updatedOAuth2User, defaultOAuth2User.getAuthorities(), defaultOAuth2User.getName());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws ServletException, IOException {
        OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) oauth2AuthenticationToken.getPrincipal();
        Map<String, Object> attributes = defaultOAuth2User.getAttributes();
        String email = (String) attributes.getOrDefault("email", "");
        String name = (String) attributes.getOrDefault("name", "");
        userRepository.findByEmail(email).ifPresentOrElse(
                user -> {
                    setAuthentication(user, defaultOAuth2User);
                },
                () -> {
                    User user = User.builder()
                            .email(email)
                            .name(name)
                            .role(UserRole.USER)
                            .build();
                    userRepository.save(user);
                    setAuthentication(user, defaultOAuth2User);
                }
        );
        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(authorizedRedirectUri);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
