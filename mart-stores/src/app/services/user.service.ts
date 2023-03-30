import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Users } from '../model/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,) {}

  registerUser(user:Users){
    const URL = `${environment.apiUrl}/user/new`;
    return this.http.post<any>(URL,user);
  }

}
