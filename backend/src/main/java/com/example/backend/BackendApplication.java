package com.example.backend;

import com.example.backend.config.mailing.token.ConfirmationToken;
import com.example.backend.config.mailing.token.ConfirmationTokenRepository;
import com.example.backend.recipe.Recipe;
import com.example.backend.recipe.RecipeRepository;
import com.example.backend.role.Role;
import com.example.backend.role.RoleRepository;
import com.example.backend.user.User;
import com.example.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories
@RequiredArgsConstructor
public class BackendApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role(1L, "USER", new ArrayList<>()));
        roleRepository.save(new Role(2L, "ADMIN", new ArrayList<>()));
        Role role = roleRepository.findRoleByName("USER");

        userRepository.save(new User(1L, "user1", "bartekmark00@gmail.com", "$2a$10$MOb8H0AkOeKMB0y0czrSCeCcFbMop.oZv4Dw8m7Q0FDKSBTt9UDOS", true, true, false, "R4CVOD4T7JRH4P5T", new ArrayList<>(), role));
        User user = userRepository.findUserByEmail("bartekmark00@gmail.com").get();

        confirmationTokenRepository.save(new ConfirmationToken(1L, "036e6f3a-bd6d-4455-b752-821a333d8429", Instant.now(), Instant.now(), Instant.now(), user));

        recipeRepository.save(new Recipe(1L, LocalDateTime.now(), "Testowy przepis", "Testowa kategoria", "testowy opis", null, "składnik 1, składnik 2", "krok 1, krok 2", user));
        recipeRepository.save(new Recipe(1L, LocalDateTime.now(), "solnik", "zupy", "Ale się zrobiła świąteczna atmosfera", null, "woda, sól, opcjonalnie śnieg", "wziąć jedno z drugim i zagotować", user));
    }
}
