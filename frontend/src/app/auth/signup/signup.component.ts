import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { SignupRequestModel } from './signup.request.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupRequestModel!: SignupRequestModel;
  signupForm!: FormGroup;

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) {

    this.signupRequestModel = {
      email: '',
      username: '',
      password: '',
      using2FA: false
    };
  }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.pattern("[a-zA-z]{3,20}[0-9]*")]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.min(8),
        Validators.max(20),
        Validators.pattern('^(?=.*\\d).{8,}$')]),
      using2FA: new FormControl(false, [])
    })
  }

  signup() {
    this.signupRequestModel = {
      email: this.signupForm.get('email')?.value,
      username: this.signupForm.get('username')?.value,
      password: this.signupForm.get('password')?.value,
      using2FA: this.signupForm.get('using2FA')?.value
    }

    this.sendSignupRequest()
  }

  private sendSignupRequest(): void {
    this.authService.signup(this.signupRequestModel)
      .subscribe({
        next: () => {
          this.router.navigate(['/login'], { queryParams: { registered: 'true' } });
        },
        error: () => {
          this.toastr.error('User with this data already exists! Try another email or username');
        }
      });
  }

  getUsername() {
    return this.signupForm.get('username');
  }
}
