import { Component, OnInit, Input  } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-section-hero-profile',
  templateUrl: './section-hero-profile.component.html',
  styleUrls: ['./section-hero-profile.component.css']
})
export class SectionHeroProfileComponent  implements OnInit{

  @Input() userId: number;
  userWallpaper: any = 'https://images2.alphacoders.com/838/thumbbig-838697.webp';


  constructor (private userService: UserService){}

  ngOnInit() {

    this.userService.getUserHero(this.userId).subscribe(
      (response: any) => {

        this.userWallpaper = response?.userWallpaper || this.userWallpaper;
      },
      (error) => {
        console.error('Erro ao buscar informações do usuário:', error);
      }
    );
  }
}
