import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl } from '@angular/forms';
import { UserSettingsService } from '../../services/user-settings.service';

@Component({
  selector: 'app-edit-educational-bakground-modal',
  templateUrl: './edit-educational-bakground-modal.component.html',
  styleUrls: ['./edit-educational-bakground-modal.component.css']
})
export class EditEducationalBakgroundModalComponent {
  userCourse = new FormControl();
  userDegree = new FormControl();
  userInstitution = new FormControl ();
  startYear = new FormControl ();
  endYear = new FormControl ();
  isCurrent = new FormControl();
  description = new FormControl();
  yearOptions: number[] = Array.from({ length: 101 }, (_, index) => new Date().getFullYear() - index);
  degreeOptions: string[] = [
    "Ensino Fundamental",
    "Ensino Médio",
    "Técnico",
    "Graduação",
    "Pós-Graduação",
    "Mestrado",
    "Doutorado",
    "Outro"
  ];

constructor(public dialogRef: MatDialogRef<EditEducationalBakgroundModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private userSettings: UserSettingsService) {
  this.userDegree.setValue(data.degree);
  this.userCourse.setValue(data.course);
  this.userInstitution.setValue(data.institution);
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
    course: this.userCourse.value,
    degree: this.userDegree.value,
    institution: this.userInstitution.value,
    startYear: this.startYear.value,
    endYear: this.endYear.value,
    description: this.description.value,
    id: this.data.id,
  };
  this.userSettings.editUserEducationalBackground(body).subscribe(
    (response) => {
      window.location.reload();
    },
    (error) => {
      console.log(error);
    }

  );
}

deleteChanges(){
  this.userSettings.deleteUserEducationalBackground(this.data.id).subscribe(
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
