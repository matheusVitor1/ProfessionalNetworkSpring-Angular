import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-page-networkng',
  templateUrl: './page-networkng.component.html',
  styleUrls: ['./page-networkng.component.css']
})
export class PageNetworkngComponent implements OnInit{

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute){}
  userRecords:any [] = [];
  recordResult = {};

  userWallpaper: any = 'https://images2.alphacoders.com/838/thumbbig-838697.webp';

  ngOnInit(){

    this.userService.getAllUsersHero().subscribe(
      (response: any) => {
          this.userRecords = response;
       

      },
      (error) => {
        console.log(error)
      }

    );
  }

  navigateToNetworkPage(data: any): void {
    this.router.navigate(['/userProfile'], { queryParams: { userId: data } });
  }



}
