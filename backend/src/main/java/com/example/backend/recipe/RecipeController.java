package com.example.backend.recipe;

import com.example.backend.recipe.dto.CreateRecipeDto;
import com.example.backend.recipe.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        RecipeDto recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("profile/{username}")
    public ResponseEntity<List<RecipeDto>> getRecipesAddedByUser(@PathVariable String username) {
        List<RecipeDto> recipes = recipeService.getRecipesAddedByUser(username);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> getRecipesWithNameOrFromCategory(@Nullable @RequestParam String name, @Nullable @RequestParam String category) {
        List<Recipe> recipes = recipeService.getRecipesWithNameOrFromCategory(name, category);
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody CreateRecipeDto createRecipeDto) {
        Recipe addedRecipe = recipeService.addNewRecipe(createRecipeDto);
        return new ResponseEntity<>(addedRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeRecipeById(@PathVariable Long id) {
        recipeService.removeRecipeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = recipeService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
