import { Component, OnInit, Input  } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-section-about-profile',
  templateUrl: './section-about-profile.component.html',
  styleUrls: ['./section-about-profile.component.css']
})
export class SectionAboutProfileComponent implements OnInit{

  @Input() userId: number;
  userPhoto: string = "";
  jobPosition: string = "";
  userAge: number = 0;
  userEmail: string = "";
  userIntroduction: string = "";
  userUf: string = "";
  userCity: string = "";
  userPhone: string = "";

  constructor (private userService: UserService){}


  ngOnInit() {

    this.userService.getUserAbout(this.userId).subscribe(
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
}
