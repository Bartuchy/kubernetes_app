package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnabled = TRUE WHERE u.email = ?1")
    void enableUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isNonLocked = TRUE WHERE u.email = ?1")
    void unlockAccount(String email);

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isUsing2FA = TRUE WHERE u.email = ?1")
    void updateUser2FA();


    Optional<User> findUserByEmail(String email);

    Optional<User> findByUsername(String username);
}
