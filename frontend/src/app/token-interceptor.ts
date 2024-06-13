import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, catchError, filter, Observable, switchMap, take, throwError } from "rxjs";
import { LoginResponse } from "./auth/login/login.response.model";
import { AuthService } from "./auth/shared/auth.service";

@Injectable({
    providedIn: 'root'
})

export class TokenInterceptor implements HttpInterceptor {

    constructor(public authService: AuthService) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

    if (req.url.indexOf('login') !== -1) {
        return next.handle(req);
    }

    const jwtToken = this.authService.getJwtToken();
    if (jwtToken) {
        return next.handle(this.addToken(req, jwtToken))
    }
    return next.handle(req);

}

addToken(req: HttpRequest<any>, jwtToken: any) {
    return req.clone({
        headers: req.headers.set('Authorization',`Bearer ` + jwtToken)
    });
}

}
