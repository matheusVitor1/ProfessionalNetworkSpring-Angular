import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { UserSettingsService } from '../../services/user-settings.service';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-add-educational-bakground-modal',
  templateUrl: './add-educational-bakground-modal.component.html',
  styleUrls: ['./add-educational-bakground-modal.component.css']
})
export class AddEducationalBakgroundModalComponent {
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
  constructor(
    public dialogRef: MatDialogRef<AddEducationalBakgroundModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
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
        course: this.userCourse.value,
        degree: this.userDegree.value,
        institution: this.userInstitution.value,
        startYear: this.startYear.value,
        endYear: this.endYear.value,
        description: this.description.value,
        userId: this.data,
      };
      this.userSettings.saveUserEducationalBackground(body).subscribe(
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
