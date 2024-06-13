import { Component, OnInit } from '@angular/core';
import { RecipeModel } from '../shared/recipe-model';
import { RecipeService } from '../shared/recipe.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  recipes: Array<RecipeModel> = [];

  constructor(private recipeService: RecipeService) {
    this.recipeService.getAllRecipes().subscribe(recipe => {
      this.recipes = recipe;
    })
  }

  ngOnInit(): void {}
}
