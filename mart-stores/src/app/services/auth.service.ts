import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Users } from '../model/users';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}
  isLoggedIn: Subject<boolean> = new Subject<boolean>();

  loginUser(user: Users) {
    const URLS = `${environment.loginUrl}`;
    return this.http.post<any>(URLS, user).pipe(
      tap((response) => {
        if (response && response.token) {
          this.isLoggedIn.next(true);
          localStorage.setItem('currentUser', response.token);
        }
      })
    );
  }

  getToken(): string | null {
    const currentUser = localStorage.getItem('currentUser');
    return currentUser;
  }

  logoutToken() {
    localStorage.removeItem('currentUser');
  }

  getData() {
    const token = this.getToken();

    const headers = { Authorization: `Bearer ${token}` };

    return this.http.get<any>('http://localhost:8080/user/userdetails', {
      headers,
    });
  }
}
