package com.example.backend.recipe;

import com.example.backend.recipe.dto.CreateRecipeDto;
import com.example.backend.recipe.dto.RecipeDto;
import com.example.backend.recipe.exception.ForbiddenException;
import com.example.backend.recipe.exception.RecipeNotFoundException;
import com.example.backend.recipe.mapper.RecipeMapper;
import com.example.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final RecipeMapper recipeMapper;

    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(RecipeDto::new)
                .toList();
    }

    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);
        return new RecipeDto(recipe);
    }

    public List<RecipeDto> getRecipesAddedByUser(String username) {
        return recipeRepository
                .findRecipesAddedByUser(username)
                .orElseThrow(RecipeNotFoundException::new)
                .stream()
                .map(RecipeDto::new)
                .toList();

    }

    public List<Recipe> getRecipesWithNameOrFromCategory(String name, String category) {
        if (name == null ^ category == null) {
            if (name != null) {
                return recipeRepository
                        .findByNameContainingIgnoreCaseOrderByDateDesc(name)
                        .orElseThrow(RecipeNotFoundException::new);
            }
            return recipeRepository
                    .findByCategoryContainingIgnoreCaseOrderByDateDesc(category)
                    .orElseThrow(RecipeNotFoundException::new);
        }
        throw new RecipeNotFoundException();
    }

    public Recipe addNewRecipe(CreateRecipeDto createRecipeDto) {
        Recipe recipe = recipeMapper.createRecipeDtoToRecipeEntity(createRecipeDto);
        return recipeRepository.save(recipe);
    }

    @Transactional
    public void updateRecipe(Long id, Recipe recipe) {
        Recipe updatedRecipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (checkCompatibility(updatedRecipe)) {
            updateRecipeFields(updatedRecipe, recipe);
        } else {
            throw new ForbiddenException();
        }
    }

    private void updateRecipeFields(Recipe updatedRecipe, Recipe recipe) {
        updatedRecipe.setName(recipe.getName());
        updatedRecipe.setCategory(recipe.getCategory());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setIngredients(recipe.getIngredients());
        updatedRecipe.setDirections(recipe.getDirections());
        updatedRecipe.setDate(LocalDateTime.now());
    }

    public void removeRecipeById(Long id) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (checkCompatibility(recipe)) {
            recipeRepository.delete(recipe);
        } else {
            throw new ForbiddenException();
        }
    }

    private boolean checkCompatibility(Recipe recipe) {
        Long loggedInUserId = userService.getLoggedInUser().getId();
        Long recipeAuthorId = recipe.getUser().getId();
        return Objects.equals(loggedInUserId, recipeAuthorId);
    }

    public List<String> getAllCategories() {
        return recipeRepository.findAll()
                .stream()
                .map(Recipe::getCategory)
                .toList();
    }
}
