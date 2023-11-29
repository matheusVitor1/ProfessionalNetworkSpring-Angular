import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl } from '@angular/forms';
import { UserSettingsService } from '../../services/user-settings.service';


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
  isCurrent = new FormControl();
  description = new FormControl();
  yearOptions: number[] = Array.from({ length: 101 }, (_, index) => new Date().getFullYear() - index);

constructor(public dialogRef: MatDialogRef<EditRecordModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private userSettings: UserSettingsService) {
  this.userJobPosition.setValue(data.jobPosition);
  this.userCompany.setValue(data.company);
  this.startYear.setValue(data.startYear);
  this.description.setValue(data.description);

  if (data.endYear === 'Atual') {
    this.endYear.disable();
    this.endYear.setValue("Atual");
    this.isCurrent.setValue(true);
  } else {
    this.endYear.setValue(data.endYear);
  }
}

  onCheckboxChange(): void {

    if (this.isEndYearDisabled()) {
      this.endYear.disable();
    } else {
      this.endYear.enable();
    }
  }


  isEndYearDisabled(): boolean {
    return this.isCurrent.value;
  }

  saveChanges(){
    const body = {
      jobPosition: this.userJobPosition.value,
      company: this.userCompany.value,
      startYear: this.startYear.value,
      endYear: this.endYear.value,
      description: this.description.value,
      id: this.data.id,
    };
    this.userSettings.editUserEmployment(body).subscribe(
      (response) => {
        window.location.reload();
      },
      (error) => {
        console.log(error);
      }

    );
  }

  deleteChanges(){
    this.userSettings.deleteUserEmployment(this.data.id).subscribe(
      (response) => {
        window.location.reload();
      },
      (error) => {
        console.log(error);
      }
    );
  }


  onCloseClick(): void {
    this.dialogRef.close();
  }
}
