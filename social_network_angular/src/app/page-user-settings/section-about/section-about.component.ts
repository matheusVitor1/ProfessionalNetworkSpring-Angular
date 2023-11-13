import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-section-about',
  templateUrl: './section-about.component.html',
  styleUrls: ['./section-about.component.css']
})
export class SectionAboutComponent implements OnInit{

  constructor (private userService: UserService){

  }

  private userData: any;
  userPhoto: string = "";
  userName: string = "";
  userBirthday: string = "";
  userAge: number = 0;
  userEmail: string = "";

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if(userState){
      this.userData = userState;
    }

    this.userService.getUserInformation(this.userData.userId).subscribe (
      (response: any) => {
        this.userName = response.name;
        this.userAge = response.age;
        this.userBirthday = response.birthday;
        this.userPhoto = response.photoUrl;
        this.userEmail = response.email;
      }
    );
  }

}
