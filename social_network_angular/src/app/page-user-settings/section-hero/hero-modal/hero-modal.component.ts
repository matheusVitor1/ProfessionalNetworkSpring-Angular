import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { UserSettingsService } from '../../services/user-settings.service';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-hero-modal',
  templateUrl: './hero-modal.component.html',
  styleUrls: ['./hero-modal.component.css']
})
export class HeroModalComponent {
  private userData: any;

  userNickNameControl = new FormControl();
  userPhotoControl = new FormControl();
  userNameControl = new FormControl();
  userWallpaperControl = new FormControl();
  userSelfDescriptionControl = new FormControl();
  selectedColor: string = 'white';

  constructor(
    public dialogRef: MatDialogRef<HeroModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService, private userSettings: UserSettingsService, private http: HttpClient ) {}

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();

      if (userState) {
        this.userData = userState;
      }

      this.userService.getUserHero(this.userData.userId).subscribe(
        (response: any) => {
          this.userWallpaperControl.setValue(response?.userWallpaper);
          this.userNickNameControl.setValue(response?.userNickName);
          this.userPhotoControl.setValue(response?.userPhoto) ;
          this.userSelfDescriptionControl.setValue(response?.userSelfDescription) ;
        },
        (error) => {
          console.error('Erro ao buscar informações do usuário:', error);
        }
      );
    }


    saveChanges() {

      const body = {
        userPhoto: this.userPhotoControl.value,
        userNickName: this.userNickNameControl.value,
        userWallpaper: this.userWallpaperControl.value,
        userSelfDescription: this.userSelfDescriptionControl.value,
        textColor: this.selectedColor,
        userId: this.userData.userId,
      };

      this.userSettings.saveUserHero(body).subscribe(
        (response) => {

          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    }



  onCloseClick(): void {
    this.dialogRef.close();
  }
}
