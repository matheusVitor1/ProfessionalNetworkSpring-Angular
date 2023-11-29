
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from './../../services/user.service';
import { AboutModalComponent } from './about-modal/about-modal.component';
import { UserSettingsService } from '../services/user-settings.service';


@Component({
  selector: 'app-section-about',
  templateUrl: './section-about.component.html',
  styleUrls: ['./section-about.component.css']
})
export class SectionAboutComponent implements OnInit{

  private userData: any;
  userPhoto: string = "";
  jobPosition: string = "";
  userAge: number = 0;
  userEmail: string = "";
  userIntroduction: string = "";
  userUf: string = "";
  userCity: string = "";
  userPhone: string = "";

  constructor (private userService: UserService, public dialog: MatDialog, private userSettings: UserSettingsService){}



  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if(userState){
      this.userData = userState;
    }

    this.userService.getUserAbout(this.userData.userId).subscribe(
      (response: any) => {
        this.userPhoto = response?.userPhoto;
        this.userEmail = response?.userEmail;
        this.jobPosition = response?.jobPosition;
        this.userIntroduction = response?.userIntroduction;
        this.userAge = response?.userAge;
        this.userCity = response?.city;
        this.userUf = response?.uf;
        this.userPhone = response?.userPhone;
        console.log(this.userPhoto);
      },
      (error) => {
        console.error('Erro ao buscar informações do usuário:', error);
      }
    );
  }

  openAboutModal(): void {
    const dialogRef = this.dialog.open(AboutModalComponent, {
      width: '50%',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Modal fechada', result);
    });
  }
}


