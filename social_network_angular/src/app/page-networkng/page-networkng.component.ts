import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-page-networkng',
  templateUrl: './page-networkng.component.html',
  styleUrls: ['./page-networkng.component.css']
})
export class PageNetworkngComponent implements OnInit{

  constructor(private userService: UserService){}

  private userData: any;
  userRecords:any [] = [];
  recordResult = {};

  ngOnInit(){
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getAllUsersHero().subscribe(
      (response: any) => {
          this.userRecords = response;

      },
      (error) => {
        console.log(error)
      }

    );
  }


}
