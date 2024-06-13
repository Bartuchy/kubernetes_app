package com.example.backend.user.auth;

import com.example.backend.config.QRCodeGenerator;
import com.example.backend.config.mailing.token.ConfirmationTokenService;
import com.example.backend.user.User;
import com.example.backend.user.UserService;
import com.example.backend.user.auth.dto.AuthenticationRequest;
import com.example.backend.user.auth.dto.AuthenticationResponse;
import com.example.backend.user.auth.dto.RegisterRequestDto;
import com.example.backend.user.auth.dto.RegisterResponseDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ConfirmationTokenService confirmationTokenService;
    private final QRCodeGenerator qrCodeGenerator;
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterRequestDto request
    ) throws MessagingException, IOException {
        authenticationService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("confirm-registration")
    public ResponseEntity<RegisterResponseDto> confirmRegistration(
            @RequestParam String confirmationToken
    ) throws UnsupportedEncodingException {
        authenticationService.confirmRegistration(confirmationToken);
        User user = confirmationTokenService.getUserByToken(confirmationToken);

        RegisterResponseDto registerResponseDto =
                new RegisterResponseDto(user.getIsUsing2FA(), qrCodeGenerator.generateQRUrl(user));
        return ResponseEntity.ok(registerResponseDto);
    }

    @GetMapping("/{email}/qr-code")
    public ResponseEntity<String> getGrCodeForUser(@PathVariable String email) throws UnsupportedEncodingException {
        User user = userService.getUserByEmail(email);
        String qrCodeLink = qrCodeGenerator.generateQRUrl(user);
        return ResponseEntity.ok(qrCodeLink);
    }
}
