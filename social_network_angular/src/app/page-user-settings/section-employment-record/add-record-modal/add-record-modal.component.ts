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
  constructor(
    public dialogRef: MatDialogRef<AddRecordModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService, private userSettings: UserSettingsService, private http: HttpClient ) {}


    onCloseClick(): void {
      this.dialogRef.close();
    }
}
