import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeModel } from '../recipe-model';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-tile',
  templateUrl: './recipe-tile.component.html',
  styleUrls: ['./recipe-tile.component.css']
})
export class RecipeTileComponent implements OnInit {
  @Input() recipes: Array<RecipeModel> = [];
  @Input() inProfile: boolean = false;

  constructor(private recipeService: RecipeService, private router: Router) {
    
  }

  ngOnInit(): void {
  }

  readRecipe(id: number): void {
    this.router.navigateByUrl('read-recipe/' + id)
  }

  deleteRecipe(id: number) {
    this.recipeService.deleteRecipeById(id).subscribe(() => {
      window.location.reload();
    });
  }

  updateRecipe(id: number) {
    this.router.navigateByUrl('update-recipe/' + id);
  }
}
