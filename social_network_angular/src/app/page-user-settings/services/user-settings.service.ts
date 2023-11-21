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



}
