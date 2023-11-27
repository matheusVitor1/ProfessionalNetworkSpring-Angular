import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { UserSettingsService } from '../../services/user-settings.service';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-record-modal',
  templateUrl: './edit-record-modal.component.html',
  styleUrls: ['./edit-record-modal.component.css']
})
export class EditRecordModalComponent {

  userJobPosition = new FormControl();
  userCompany = new FormControl ();
  startYear = new FormControl ();
  endYear = new FormControl ();
  
  constructor(
    public dialogRef: MatDialogRef<EditRecordModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService, private userSettings: UserSettingsService, private http: HttpClient ) {

      this.userJobPosition.setValue(data.jobPosition);
      this.userCompany.setValue(data.company);
      this.startYear.setValue(data.startYear);
      this.endYear.setValue(data.endYear);
    }

    



  onCloseClick(): void {
    this.dialogRef.close();
  }
}
