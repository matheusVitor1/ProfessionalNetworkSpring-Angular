import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from 'src/app/local-storage.service';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserSettingsService {


  constructor(private http: HttpClient, private localStorageService: LocalStorageService) { }

  saveUserHero(body: any): Observable<any>{
    const url = 'http://localhost:8080/hero/new';
    return this.http.post(url, body);
  }

  saveUserAbout(body: any): Observable<any>{
    const url = 'http://localhost:8080/about/new';
    return this.http.post(url, body);
  }


  saveUserEmployment(body: any): Observable<any>{
    const url = 'http://localhost:8080/employmentRecord/new';
    return this.http.post(url, body);
  }

  editUserEmployment(body: any): Observable<any>{
    const url = 'http://localhost:8080/employmentRecord/edit';
    return this.http.put(url, body);
  }

  deleteUserEmployment(id: number): Observable<any>{
    const url = `http://localhost:8080/employmentRecord/remove/${id}`;
    return this.http.delete(url);
  }

  saveUserEducationalBackground(body: any): Observable<any>{
    const url = 'http://localhost:8080/educationalBackground/new';
    return this.http.post(url, body);
  }

  editUserEducationalBackground(body: any): Observable<any>{
    const url = 'http://localhost:8080/educationalBackground/edit';
    return this.http.put(url, body);
  }

  deleteUserEducationalBackground(id: number): Observable<any>{
    const url = `http://localhost:8080/educationalBackground/remove/${id}`;
    return this.http.delete(url);
  }


}
