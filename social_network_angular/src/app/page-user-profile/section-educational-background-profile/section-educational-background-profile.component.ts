import { Component, OnInit, Input  } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-section-educational-background-profile',
  templateUrl: './section-educational-background-profile.component.html',
  styleUrls: ['./section-educational-background-profile.component.css']
})
export class SectionEducationalBackgroundProfileComponent implements OnInit{

  @Input() userId: number;
  userEduRecord:any [] = [];

  constructor (private userService: UserService){}

  ngOnInit(){

    this.userService.getUserEduRecord(this.userId).subscribe(
      (response: any) => {
          this.userEduRecord = response;
      },
      (error) => {
        console.log(error)
      }

    );
  }
}
