import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateRecipeDto } from '../recipe/add-recipe/create.recipe.dto';
import { RecipeModel } from './recipe-model';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private http: HttpClient) { }

  getAllRecipes(): Observable<Array<RecipeModel>> {
    return this.http.get<Array<RecipeModel>>('/api/api/recipe/all')
  }

  addRecipe(createRecipeDto: CreateRecipeDto): Observable<any> {
    return this.http.post('/api/api/recipe/add', createRecipeDto)
  }

  getRecipeById(id: number): Observable<RecipeModel> {
    return this.http.get<RecipeModel>('/api/api/recipe/' + id)
  }

  getAllRecipesAddedByUser(username: string): Observable<Array<RecipeModel>> {
    return this.http.get<Array<RecipeModel>>('/api/api/recipe/profile/' + username);
  }

  deleteRecipeById(id: number): Observable<any> {
    return this.http.delete('/api/api/recipe/remove/' + id);
  }

  updateRecipe(id: number, recipePayload: RecipeModel): Observable<any> {
    return this.http.put<RecipeModel>('/api/api/recipe/update/' + id, recipePayload);
  }

  getAllCategories(): Observable<Array<string>> {
    return this.http.get<Array<string>>('/api/api/recipe/categories')
  }
}
