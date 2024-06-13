import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { LoginRequestModel } from "../login/login.request.model";
import { AuthService } from "../shared/auth.service";

@Component({
    selector: 'app-qr-auth',
    templateUrl: './qr-auth.component.html',
    styleUrls: ['./qr-auth.component.css']
})
export class QrAuthComponent implements OnInit {
    loginRequest: LoginRequestModel = { email: '', password: '', verificationCode: '' };
    qrCode: string = '';
    verificationCode: string = '';

    constructor(private authService: AuthService,
                private activatedRoute: ActivatedRoute,
                private router: Router,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(params => {
            const payload = params['payload'];
            if (payload) {
                this.loginRequest = JSON.parse(payload) as LoginRequestModel;
            }
        });

        this.authService.getQrCodeLinkForUser(this.loginRequest.email)
            .subscribe(v => {
              console.log(v)
                this.qrCode = v;
            })
    }

    checkAndNavigate() {
        if (this.verificationCode.length == 6) {

            this.loginRequest = {
                email: this.loginRequest.email,
                password: this.loginRequest.password,
                verificationCode: this.verificationCode
            }
            console.log(this.loginRequest);
            this.authService.login(this.loginRequest).subscribe({
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
