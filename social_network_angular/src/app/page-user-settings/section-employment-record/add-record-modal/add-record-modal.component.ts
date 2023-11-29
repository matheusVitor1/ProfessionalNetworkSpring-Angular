import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { UserSettingsService } from '../../services/user-settings.service';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-record-modal',
  templateUrl: './add-record-modal.component.html',
  styleUrls: ['./add-record-modal.component.css']
})
export class AddRecordModalComponent {

  userJobPosition = new FormControl();
  userCompany = new FormControl ();
  startYear = new FormControl ();
  endYear = new FormControl ();
  isCurrent = new FormControl();
  description = new FormControl();
  yearOptions: number[] = Array.from({ length: 101 }, (_, index) => new Date().getFullYear() - index);

  constructor(
    public dialogRef: MatDialogRef<AddRecordModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService, private userSettings: UserSettingsService, private http: HttpClient ) {
      console.log(data);
    }



    onCheckboxChange(): void {

      if (this.isEndYearDisabled()) {
        this.endYear.setValue("Atual");
        this.endYear.disable();
      } else {
        this.endYear.enable();
      }
    }


    isEndYearDisabled(): boolean {
      return this.isCurrent.value;
    }

    save(){
      const body = {
        jobPosition: this.userJobPosition.value,
        company: this.userCompany.value,
        startYear: this.startYear.value,
        endYear: this.endYear.value,
        description: this.description.value,
        userId: this.data,
      };
      this.userSettings.saveUserEmployment(body).subscribe(
        (response) => {
          window.location.reload();
        },
        (error) => {
          console.log(error);
          console.log(this.data.userId);
        }

      );
    }


    onCloseClick(): void {
      this.dialogRef.close();
    }
}
