import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs';
import { AuthService } from '../shared/auth.service';
import { LoginRequestModel } from './login.request.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  loginRequestPayload: LoginRequestModel;
  isError?: boolean;
  registerSuccessMessage?: string;

  constructor(private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) {
    this.loginRequestPayload = {
      email: '',
      password: '',
      verificationCode: ''
    }
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required,
          Validators.min(8),
          Validators.max(20),
          Validators.pattern('^(?=.*\\d).{8,}$')
      ])
    });

    this.activatedRoute.queryParams.subscribe(params => {
      if (params['registered'] !== undefined && params['registered'] === 'true') {
        this.toastr.success('Signup Successful');
        this.registerSuccessMessage = 'You can log in now!';
      }
    });
  }

  login() {
    let email = this.loginForm.get('email')?.value;
    this.authService.using2FA(email)
      .subscribe(using2FA => {

        this.loginRequestPayload = {
          email: email,
          password: this.loginForm.get('password')?.value,
          verificationCode: ''
        }
        this.manageRedirection(using2FA);
      });
  }

  manageRedirection(using2FA: boolean) {
    if (using2FA) {
      this.router.navigate(['/qr-auth'], {
        queryParams: { payload: JSON.stringify(this.loginRequestPayload) }
      });
    } else {
      this.authService.login(this.loginRequestPayload).subscribe({
        next: () => {
          this.router.navigateByUrl('/home');
        },
        error: () => {
          this.toastr.error('Something went wrong', 'Error');
        }
      });
    }
  }
}
