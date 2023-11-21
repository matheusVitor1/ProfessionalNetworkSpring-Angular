import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-section-hero',
  templateUrl: './section-hero.component.html',
  styleUrls: ['./section-hero.component.css']
})
export class SectionHeroComponent implements OnInit {

  constructor(public dialog: MatDialog,private userService: UserService){}

  private userData: any;
  userWallpaper: any;

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getUserHero(this.userData.userId).subscribe(
      (response: any) => {
        this.userWallpaper = response?.userWallpaper;
      },
      (error) => {
        console.error('Erro ao buscar informações do usuário:', error);
      }
    );
  }

}
