import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-logged-in',
  templateUrl: './header-logged-in.component.html',
  styleUrls: ['./header-logged-in.component.css']
})
export class HeaderLoggedInComponent implements OnInit {
  private userData: any;

  userName: any;
  userPhoto: any;

  constructor (private userService: UserService, private router: Router) {}

  logout() {
    this.userService.logoutUser();
    this.router.navigate(['/login']);
  }

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getUserInformation(this.userData.userId).subscribe(
      (response: any) => {
        this.userPhoto = response?.photoUrl; // Usando o Elvis Operator
        this.userName = response?.name; // Usando o Elvis Operator
      },
      (error) => {
        console.error('Erro ao buscar informações do usuário:', error);
      }
    );
  }
}
