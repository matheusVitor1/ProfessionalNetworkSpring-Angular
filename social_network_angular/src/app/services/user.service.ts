import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { LocalStorageService } from '../local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly AUTH_KEY = 'isAuthenticated';
  private readonly USER_INFO_KEY = 'userInfo';

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isAuthenticated());
  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  localStorageSetUserInfo(userInfo: any): void {
    this.localStorageService.set(this.USER_INFO_KEY, userInfo);
  }

  localStorageGetUserInfo(): any {
    return this.localStorageService.get(this.USER_INFO_KEY);
  }

  addUser(body: any): Observable<any>{
    const url = 'http://localhost:8080/user/new';
    return this.http.post(url, body);
  }

  loginUser(params: HttpParams): Observable<any> {
    const url = 'http://localhost:8080/login/auth';
    return this.http.post(url, null, { params });
  }

  getUserInformation(userId: number) {
    const url = `http://localhost:8080/user/${userId}`;
    return this.http.get(url);
  }

  getUserAddress(userId: number){
    const url = `http://localhost:8080/address/${userId}`;
    return this.http.get(url);
  }

  getUserHero(userId: number){
    const url = `http://localhost:8080/hero/${userId}`;
    return this.http.get(url);
  }

  getUserAbout(userId: number){
    const url = `http://localhost:8080/about/${userId}`;
    return this.http.get(url);
  }

  getUserEmloymentRecord(userId: number){
    const url = `http://localhost:8080/EmploymentRecord/${userId}`;
    return this.http.get(url);
  }



  setAuthenticated(isAuthenticated: boolean): void {
    this.localStorageService.set(this.AUTH_KEY, isAuthenticated);
    this.isAuthenticatedSubject.next(isAuthenticated);
  }

  isAuthenticated(): boolean {
    return this.localStorageService.get(this.AUTH_KEY) === true;
  }

  logoutUser(): void {
    this.setAuthenticated(false);
    this.localStorageService.remove(this.USER_INFO_KEY);
  }

}
