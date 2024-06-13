import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './auth/signup/signup.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './auth/login/login.component';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BrowserAnimationsModule } from'@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './home/home.component';
import { TokenInterceptor } from './token-interceptor';
import { RecipeTileComponent } from './shared/recipe-tile/recipe-tile.component';
import { SideBarComponent } from './shared/side-bar/side-bar.component';
import { AddRecipeComponent } from './recipe/add-recipe/add-recipe.component';
import { EditorModule } from '@tinymce/tinymce-angular';
import { ViewRecipeComponent } from './recipe/view-recipe/view-recipe.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserProfileComponent } from './auth/user-profile/user-profile.component';
import { UpdateRecipeComponent } from './recipe/update-recipe/update-recipe.component';
import { QrAuthComponent } from "./auth/qr-auth/qr-auth.component";
import { NgOptimizedImage } from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    RecipeTileComponent,
    SideBarComponent,
    AddRecipeComponent,
    ViewRecipeComponent,
    UserProfileComponent,
    UpdateRecipeComponent,
    QrAuthComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        NgxWebstorageModule.forRoot(),
        BrowserAnimationsModule,
        ToastrModule.forRoot(),
        EditorModule,
        NgbModule,
        FormsModule,
        NgOptimizedImage
    ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
