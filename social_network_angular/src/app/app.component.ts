import { Component, OnInit, HostListener, TemplateRef, VERSION, ViewChild   } from '@angular/core';
import { UserService } from './services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'social.network.app';
  private userData: any;
  isAuthenticated: boolean = false;


  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
      this.isAuthenticated = true;
    }

    this.userService.isAuthenticated$.subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });
  }

  @HostListener('window:beforeunload', ['$event'])
  beforeUnloadHandler(event: any) {

    if (event instanceof Event) {

      if (!this.isPageReloading()) {

        this.userService.logoutUser();
      }
    }
  }

  private isPageReloading(): boolean {

    return performance && performance.navigation && performance.navigation.type === 1;
  }



}
