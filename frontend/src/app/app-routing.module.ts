import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { UserProfileComponent } from './auth/user-profile/user-profile.component';
import { HomeComponent } from './home/home.component';
import { AddRecipeComponent } from './recipe/add-recipe/add-recipe.component';
import { UpdateRecipeComponent } from './recipe/update-recipe/update-recipe.component';
import { ViewRecipeComponent } from './recipe/view-recipe/view-recipe.component';
import { QrAuthComponent } from "./auth/qr-auth/qr-auth.component";

const routes: Routes = [
  { path: '', component: AppComponent},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'sign-up', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'add-recipe', component: AddRecipeComponent, canActivate: [AuthGuard] },
  { path: 'update-recipe/:id', component: UpdateRecipeComponent, canActivate: [AuthGuard] },
  { path: 'read-recipe/:id', component: ViewRecipeComponent, canActivate: [AuthGuard] },
  { path: 'user-profile/:username', component: UserProfileComponent, canActivate: [AuthGuard] },
  {
    path: 'qr-auth',
    component: QrAuthComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
