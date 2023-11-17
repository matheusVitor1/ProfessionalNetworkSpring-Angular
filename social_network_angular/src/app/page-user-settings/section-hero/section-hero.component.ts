import { UserSettingsService } from './../services/user-settings.service';
import { Component, Renderer2, ElementRef, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';


declare var Typed: any;
@Component({
  selector: 'app-section-hero',
  templateUrl: './section-hero.component.html',
  styleUrls: ['./section-hero.component.css']
})
export class SectionHeroComponent implements OnInit {


  isAccordionOpen = false;
  toggleAccordion() {
    this.isAccordionOpen = !this.isAccordionOpen;
  }

  constructor(private userService: UserService, private userSettings: UserSettingsService, private renderer: Renderer2, private el: ElementRef, private http: HttpClient){}


  private userData: any;
  userNickName: any;
  userWallpaper: any;
  textColor: any;
  userSelfDescription: any;

  userNickNameControl = new FormControl();
  userPhotoControl = new FormControl();
  userNameControl = new FormControl();
  userWallpaperControl = new FormControl();
  userSelfDescriptionControl = new FormControl();
  selectedColor: string = 'white';

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
    }

    this.userService.getUserAddress(this.userData.userId).subscribe(
      (response: any) => {
        this.cepControl.setValue(response.cep);
        this.ufControl.setValue(response.uf);
        this.cidadeControl.setValue(response.localidade);
        console.log(response);
      }
    );


    this.userSettings.getUserHero(this.userData.userId).subscribe(
      (response: any) => {
        this.userWallpaperControl.setValue(response?.userWallpaper);
        this.textColor = response?.textColor;
        this.userNickNameControl.setValue(response?.userNickName);
        this.userPhotoControl.setValue(response?.userPhoto) ;
        this.userSelfDescriptionControl.setValue(response?.userSelfDescription) ;
        this.userWallpaper = this.userWallpaperControl.value;
        this.userNickName = this.userNickNameControl.value;
        this.userSelfDescription = this.userSelfDescriptionControl.value;
      },
      (error) => {
        console.error('Erro ao buscar informações do usuário:', error);
      }
    );
  }

  save() {
    const selectedColor = (document.getElementById('corTexto') as HTMLSelectElement).value;

    const body = {
      userPhoto: this.userPhotoControl.value,
      userNickName: this.userNickNameControl.value,
      userWallpaper: this.userWallpaperControl.value,
      userSelfDescription: this.userSelfDescriptionControl.value,
      textColor: this.selectedColor,
      userId: this.userData.userId,
    };

    this.userSettings.saveUserHero(body).subscribe(
      (response) => {
        window.location.reload();
        this.addAddress();
      },
      (error) => {
        console.log(error);
      }
    );



  }

  cepControl = new FormControl();
  ruaControl = new FormControl();
  bairroControl = new FormControl();
  cidadeControl = new FormControl();
  ufControl = new FormControl();
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
          console.log('caraioooo', requestBody);

        },
        (error) => {
          console.error('Ocorreu um erro:', error);
        }
      );
  }



}
