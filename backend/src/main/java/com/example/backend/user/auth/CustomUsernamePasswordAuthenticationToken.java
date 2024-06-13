package com.example.backend.user.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String verificationCode;

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, String verificationCode, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.verificationCode = verificationCode;
    }

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, String verificationCode) {
        super(principal, credentials);
        this.verificationCode = verificationCode;
    }
}
