import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { SignupRequestModel } from '../signup/signup.request.model';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { LoginRequestModel } from '../login/login.request.model';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginResponse } from '../login/login.response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  URL_PREFIX: string = '/api/api/auth';

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) { }

  signup(signupRequestPayload: SignupRequestModel): Observable<any> {
     return this.httpClient.post(
       '/api/api/auth/register',
        signupRequestPayload,
        { responseType: 'text' },
      )
   }

   login(loginRequestPayload: LoginRequestModel): Observable<boolean> {
    return this.httpClient.post<LoginResponse>('/api/api/auth/authenticate',
      loginRequestPayload).pipe(map(data => {
        this.localStorage.store('authenticationToken', data.authenticationToken);
        this.localStorage.store('username', data.username);
        this.localStorage.store('email', data.email);
        this.loggedIn.emit(true);
        return true;
      }));
   }

  using2FA(email: string): Observable<boolean> {
    return this.httpClient.get<boolean>(`/api/user/${email}/using2fa`);
  }

  getQrCodeLinkForUser(email: string): Observable<string> {
    return this.httpClient.get<string>(this.URL_PREFIX + `/${email}/qr-code`,{ responseType: 'text' as 'json'})

  }

  getJwtToken() {
     return this.localStorage.retrieve('authenticationToken');
  }

  getUsername() {
    return this.localStorage.retrieve('username');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
