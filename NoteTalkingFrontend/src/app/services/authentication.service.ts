import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../environments/environments';
import { User } from '../models/user';
declare var require: any;

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private loginURL = '/login';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(
    private http: HttpClient
  ) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem('currentUser') as any));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  login(userName: string, password: string) : Observable<boolean>{
    return this.http.get<any>(`${environment.apiUrl}/login?password=${password}&userName=${userName}`)
      .pipe(map(data => {
        console.log(data);
        // login successful if there's a jwt token in the response
        if (data && data.access_token) {
          this.currentUserSubject.next(data);
          sessionStorage.setItem('access_token', data.access_token);
      }

      return data;
      }));
  }

  register(user: User) : Observable<any>{
    console.log(user);
    return this.http.post<any>(`${environment.apiUrl}/register`, user, this.httpOptions)
      .pipe(map(data => {
        console.log(data);
        if (data) {
          console.log("login success =============");
          console.log(data);
        }

        return data;
      }));
  }

  logout() : Observable<boolean>{
    return this.http.get<boolean>(`${environment.apiUrl}/logout`)
      .pipe(map(data => {
        console.log(data);
        if (data) {
          console.log("logout success =============");
          console.log(data);
        }

        return data;
      }));
  }
}
