 import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpParams } from '@angular/common/http';


@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.css']
})
export class PageLoginComponent {
  isAuthenticated: boolean;
  emailControl = new FormControl();
  passwordControl = new FormControl();



  constructor(private userService: UserService, private router: Router,){
    this.isAuthenticated = userService.isAuthenticated();
  }


  login(){
    const params = new HttpParams()
      .set('email', this.emailControl.value)
      .set('password', this.passwordControl.value);

      this.userService.loginUser(params).subscribe(
        (response) => {
          const userState = {
            userId: response.id,

          };

          this.userService.localStorageSetUserInfo(userState);
          this.userService.setAuthenticated(true);
          this.router.navigate(['/userSettings']);


        },
        (error) => {
          if (error.status === 401) {
            alert("Senha incorreta");
          } else if (error.status === 404){
            alert("Email incorreto")
          }
        }
      );

  }



}
