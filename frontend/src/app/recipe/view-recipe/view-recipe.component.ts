import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { throwError } from 'rxjs';
import { RecipeModel } from 'src/app/shared/recipe-model';
import { RecipeService } from 'src/app/shared/recipe.service';

@Component({
  selector: 'app-view-recipe',
  templateUrl: './view-recipe.component.html',
  styleUrls: ['./view-recipe.component.css']
})
export class ViewRecipeComponent implements OnInit {

  recipeId!: number;
  recipe: RecipeModel = new RecipeModel();

  constructor(private recipeService: RecipeService, private activatedRoute: ActivatedRoute) {
    this.recipeId = this.activatedRoute.snapshot.params['id'];
    this.recipeService.getRecipeById(this.recipeId).subscribe((data: RecipeModel) => {
      this.recipe = data;
    }, (error: any) => {
      throwError(error);
    })
  }

  ngOnInit(): void {
  }

}
