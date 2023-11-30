import { Component, OnInit, Input  } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-section-employnent-record-profile',
  templateUrl: './section-employnent-record-profile.component.html',
  styleUrls: ['./section-employnent-record-profile.component.css']
})
export class SectionEmploynentRecordProfileComponent implements OnInit{

  @Input() userId: number;

  userEmploymentRecord:any [] = [];
  recordResult = {};


  constructor (private userService: UserService){}

  ngOnInit(){

    this.userService.getUserEmloymentRecord(this.userId).subscribe(
      (response: any) => {
          this.userEmploymentRecord = response;

      },
      (error) => {
        console.log(error)
      }

    );
  }


}
