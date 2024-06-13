package com.example.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("{email}/update/2fa")
    public ResponseEntity<Void> modifyUser2FA(@PathVariable String email) {
        userService.updateUser2FA(email, false);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{email}/using2fa")
    public ResponseEntity<Boolean> isUserUsing2FA(@PathVariable String email) {
        boolean using2FA = userService.isUserUsing2FA(email);
        return ResponseEntity.ok(using2FA);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
