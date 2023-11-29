import { Component, Renderer2, ElementRef, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { UserSettingsService } from '../page-user-settings/services/user-settings.service';
import { HeroModalComponent } from '../page-user-settings/section-hero/hero-modal/hero-modal.component';

@Component({
  selector: 'app-header-logged-in',
  templateUrl: './header-logged-in.component.html',
  styleUrls: ['./header-logged-in.component.css']
})
export class HeaderLoggedInComponent implements OnInit {
  private userData: any;
  userName: any;
  userPhoto: any;
  userWallpaper: any;
  userSelfDescription: any;

  constructor (public dialog: MatDialog,private userService: UserService, private userSettings: UserSettingsService, private renderer: Renderer2, private el: ElementRef, private router: Router) {}


  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getUserHero(this.userData.userId).subscribe(
      (response: any) => {
        this.userPhoto = response?.userPhoto;
        this.userWallpaper = response?.userWallpaper;
        this.userName = response?.userNickName;
        this.userSelfDescription = response?.userSelfDescription;

      },
      (error) => {
        console.error('Erro ao buscar informações do usuário:', error);
      }
    );
  }

  logout() {
    this.userService.logoutUser();
    this.router.navigate(['/login']);
  }

  openAboutModal(): void {
    const dialogRef = this.dialog.open(HeroModalComponent, {
      width: '50%',

    });

    dialogRef.afterClosed().subscribe(result => {
      // você pode adicionar lógica aqui para ser executada após o fechamento da modal, se necessário
      console.log('Modal fechada', result);
    });
  }
}
