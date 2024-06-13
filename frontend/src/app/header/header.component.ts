import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { animationFrameScheduler } from 'rxjs';
import { AuthService } from '../auth/shared/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  //faUser? = this.faUser;
  isLoggedIn: boolean = false;
  username: string = "";

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUsername();
    this.authService.loggedIn.subscribe((data:boolean) => this.isLoggedIn = data);
    this.authService.username.subscribe((data: string) => this.username = data);
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username)
  }

  logout() {
    localStorage.clear();
    this.isLoggedIn = false;
    this.router.navigateByUrl('').then(() => window.location.reload());
  }

}
