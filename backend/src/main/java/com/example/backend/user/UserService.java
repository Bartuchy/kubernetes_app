package com.example.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public void unlockAccount(String email) {
        userRepository.unlockAccount(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with email %s not found", email)
        ));
    }

    public Optional<User> getUSerOptByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void updateUser2FA(String email, boolean using2FA) {
        Authentication curAuth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) curAuth.getPrincipal();
        userRepository.updateUser2FA();
    }

    public boolean isUserUsing2FA(String email) {
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email %s not found", email)
                ))
                .getIsUsing2FA();
    }

    public void saveGeneratedQrCode(User user) {
        userRepository.save(user);
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return getUserByEmail(email);
    }
}
