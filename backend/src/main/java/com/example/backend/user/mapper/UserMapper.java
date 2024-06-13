package com.example.backend.user.mapper;

import com.example.backend.role.RoleService;
import com.example.backend.user.User;
import com.example.backend.user.auth.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.backend.config.security.SecurityUtil.USER;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public User registerRequestDtoToUserEntity(RegisterRequestDto registerRequestDto) {
        return User.builder()
                .username(registerRequestDto.getUsername())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(roleService.getRoleByName(USER))
                .isUsing2FA(registerRequestDto.getUsing2FA())
                .secret(Base32.random())
                .isEnabled(true)
                .isNonLocked(true)
                .build();
    }
}
