import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { AddEducationalBakgroundModalComponent } from './add-educational-bakground-modal/add-educational-bakground-modal.component';
import { EditEducationalBakgroundModalComponent } from './edit-educational-bakground-modal/edit-educational-bakground-modal.component';
@Component({
  selector: 'app-section-educational-background',
  templateUrl: './section-educational-background.component.html',
  styleUrls: ['./section-educational-background.component.css']
})
export class SectionEducationalBackgroundComponent implements OnInit{

  constructor(private userService: UserService, public dialog: MatDialog,){}

  private userData: any;
  userEduRecord:any [] = [];
  recordResult = {};

  ngOnInit(){
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getUserEduRecord(this.userData.userId).subscribe(
      (response: any) => {
          this.userEduRecord = response;

      },
      (error) => {
        console.log(error)
      }

    );
  }


  openEditRecordModal(id:any, course:string  ,degree:string, institution:string, startYear:string, endYear:string, description:String, userId:any): void {
    this.recordResult = {id, course ,degree, institution, startYear,endYear,description,userId};
    const dialogRef = this.dialog.open(EditEducationalBakgroundModalComponent, {
      width: '50%',

      data: this.recordResult,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Modal fechada', result);
    });
  }

  openAddRecordModal(){
    const dialogRef = this.dialog.open(AddEducationalBakgroundModalComponent, {
      width: '50%',
      data: this.userData.userId,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Modal fechada', result);
    });
  }
}
