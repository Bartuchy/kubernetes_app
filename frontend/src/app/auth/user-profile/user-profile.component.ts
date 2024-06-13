import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeModel } from 'src/app/shared/recipe-model';
import { RecipeService } from 'src/app/shared/recipe.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  username: string = '';
  recipes: Array<RecipeModel> = [];
  recipeLength: number = 0;

  constructor(private activatedRoute: ActivatedRoute, private recipeService: RecipeService) {

  }

  ngOnInit(): void {
    this.username = this.activatedRoute.snapshot.params['username'];
    this.recipeService.getAllRecipesAddedByUser(this.username).subscribe(data => {
      this.recipes = data;
      this.recipeLength = data.length;
    })
  }



}
