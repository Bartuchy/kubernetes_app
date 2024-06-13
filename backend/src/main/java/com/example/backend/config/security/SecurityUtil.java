package com.example.backend.config.security;

public class SecurityUtil {
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String[] PERMITTED_ALL_PATHS = new String[] {
            "/api/auth/**",
            "/api/demo-controller/test",
            "/api/auth/{email}/qr-code",
            "/user/{email}/using2fa"
    };

    public static final String[] USER_PATHS = new String[] {
            "/api/demo-controller/user"
    };

    public static final String[] ADMIN_PATHS = new String[] {
            "/api/demo-controller/admin"
    };
}
