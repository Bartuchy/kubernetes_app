package com.example.backend.user;

import com.example.backend.role.RoleService;
import com.example.backend.user.auth.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.backend.config.security.SecurityUtil.USER;

@Component
@RequiredArgsConstructor
public class UserFactory {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequestDto request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.getRoleByName(USER))
                .isUsing2FA(request.getUsing2FA())
                .build();
    }
}
