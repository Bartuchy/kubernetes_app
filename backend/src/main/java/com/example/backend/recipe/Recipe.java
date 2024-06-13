package com.example.backend.recipe;

import com.example.backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private String name;
    private String category;
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

    private String ingredients;

    private String directions;

    @ManyToOne
    @JsonIgnoreProperties(value="recipes")
    private User user;

    public Recipe(String name, String category, String description, String ingredients, String directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}
