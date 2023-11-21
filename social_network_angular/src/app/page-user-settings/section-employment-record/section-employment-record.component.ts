import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { AddRecordModalComponent } from './add-record-modal/add-record-modal.component';
import { EditRecordModalComponent } from './edit-record-modal/edit-record-modal.component';
@Component({
  selector: 'app-section-employment-record',
  templateUrl: './section-employment-record.component.html',
  styleUrls: ['./section-employment-record.component.css']
})
export class SectionEmploymentRecordComponent implements OnInit{

  constructor(private userService: UserService, public dialog: MatDialog,){}

  private userData: any;
  userEmploymentRecord:any [] = [];
  recordResult:any [] = [];

  ngOnInit(){
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getUserEmloymentRecord(this.userData.userId).subscribe(
      (response: any) => {
          this.userEmploymentRecord = response;
      },
      (error) => {
        console.log(error)
      }

    );
  }

  openEditRecordModal(id:number, jobPosition:string, company:string, startYear:string, endYear:string, description:String, userId:number): void {
    this.recordResult = [id, jobPosition, company, startYear,endYear,description,userId];
    const dialogRef = this.dialog.open(EditRecordModalComponent, {
      width: '50%',
      height: '75%',
      data: this.recordResult,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Modal fechada', result);
    });
  }

}
