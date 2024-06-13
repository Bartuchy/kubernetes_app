import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap, throwError } from 'rxjs';
import { RecipeService } from 'src/app/shared/recipe.service';
import { CreateRecipeDto } from './create.recipe.dto';
import { RecipeModel } from "../../shared/recipe-model";

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

  addRecipeForm!: FormGroup;
  recipePayload!: CreateRecipeDto;
  categories: Array<string> = new Array<string>();
  customCategory: string = "";

  constructor(private router: Router, private recipeService: RecipeService) {
    this.recipePayload = {
      name: '',
      category: '',
      description: '',
      ingredients: '',
      directions: ''
    }
  }

  ngOnInit() {
    this.recipeService.getAllCategories().pipe(
      tap((data: Array<string>) => {
        this.categories = data;
      }),
      catchError((error: any) => {
        return throwError(error);
      })
    ).subscribe();

    this.addRecipeForm = new FormGroup({
      name: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      ingredients: new FormControl('', Validators.required),
      directions: new FormControl('', Validators.required)
    });
  }

  addRecipe() {
    this.recipePayload.name = this.addRecipeForm.get('name')!.value;
    this.recipePayload.category = this.addRecipeForm.get('category')!.value;
    this.recipePayload.description = this.addRecipeForm.get('description')!.value;
    this.recipePayload.ingredients = this.addRecipeForm.get('ingredients')!.value;
    this.recipePayload.directions = this.addRecipeForm.get('directions')!.value;

    this.recipeService.addRecipe(this.recipePayload).subscribe(() => {
      this.router.navigateByUrl('/home');
    }, (error: any) => {
      throwError(error);
    })
  }

  discardRecipe() {
    this.router.navigateByUrl('/home');
  }

}
