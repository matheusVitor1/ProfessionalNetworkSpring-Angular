import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { UserSettingsService } from '../../services/user-settings.service';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-about-modal',
  templateUrl: './about-modal.component.html',
  styleUrls: ['./about-modal.component.css']
})
export class AboutModalComponent implements OnInit {

  private isFirstRecord: boolean = false;
  private userData: any;
  userJobPosition = new FormControl();
  userIntroduction = new FormControl();
  userNameControl = new FormControl();
  userPhone = new FormControl();
  userBirthday = new FormControl();


  cepControl = new FormControl();
  ruaControl = new FormControl();
  bairroControl = new FormControl();
  cidadeControl = new FormControl();
  ufControl = new FormControl();

  constructor(
    public dialogRef: MatDialogRef<AboutModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private userService: UserService, private userSettings: UserSettingsService, private http: HttpClient ) {}

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();

      if (userState) {
        this.userData = userState;
      }

      this.userService.getUserAbout(this.userData.userId).subscribe(
        (response: any) => {
          if (response) {
            this.userJobPosition.setValue(response.jobPosition);
            this.userIntroduction.setValue(response.userIntroduction);
            this.userPhone.setValue(response.userPhone);
            this.userBirthday.setValue(response.userBirthday);
            this.cepControl.setValue(response.cep);
            this.ufControl.setValue(response.uf);
            this.cidadeControl.setValue(response.city);
            console.log("teste, teste, teste",response);
          } else {


          }
        },
        (error: any) => {
            if (error.status === 500){
              this.isFirstRecord = true;
            }
        }
      );




    }

    consumeViaServiceApi() {
      const cep = this.cepControl.value;
      const cepLimpo = cep.replace(/\D/g, '');
      if (cepLimpo.length === 8) {
        const requestBody = { cep: cepLimpo };
        console.log('Requisição enviada com o corpo:', requestBody);

        this.http.post('http://localhost:8080/address/consulta', requestBody).subscribe(
          (data: any) => {
            console.log('Resposta recebida:', data);
            this.ruaControl.setValue(data.logradouro);
            this.bairroControl.setValue(data.bairro);
            this.cidadeControl.setValue(data.localidade);
            this.ufControl.setValue(data.uf);
            console.log(data);
          },
          (error) => {
            console.error('Ocorreu um erro:', error);

          }
        );
      }
    }

    addAddress() {
      const requestBody = {
        cep: this.cepControl.value,
        logradouro: this.ruaControl.value,
        bairro: this.bairroControl.value,
        localidade: this.cidadeControl.value,
        uf: this.ufControl.value,
        userId: this.userData.userId,
      }
      this.http.post('http://localhost:8080/address/new', requestBody).subscribe(
          (data: any) => {

          },
          (error) => {
            console.error('Ocorreu um erro:', error);
          }
        );
    }

    saveChanges() {

      if(this.isFirstRecord){
        this.addAddress();
      }

      const body = {
        jobPosition: this.userJobPosition.value,
        userIntroduction: this.userIntroduction.value,
        userPhone: this.userPhone.value,
        userBirthday: this.userBirthday.value,
        cep: this.cepControl.value,
        logradouro: this.ruaControl.value,
        bairro: this.bairroControl.value,
        city: this.cidadeControl.value,
        uf: this.ufControl.value,
        userId: this.userData.userId,
      };

      this.userSettings.saveUserAbout(body).subscribe(
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
