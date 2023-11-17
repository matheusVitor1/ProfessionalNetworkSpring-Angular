import { UserService } from './../../services/user.service';
import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Subscription } from 'rxjs';
import { SectionEmploymentRecordComponent } from '../section-employment-record/section-employment-record.component';

import { ModalService } from 'src/app/services/modal.service';


@Component({
  selector: 'app-section-about',
  templateUrl: './section-about.component.html',
  styleUrls: ['./section-about.component.css']
})
export class SectionAboutComponent implements OnInit{

  isAccordionOpen = false;
  toggleAccordion() {
    this.isAccordionOpen = !this.isAccordionOpen;
  }

  constructor (private modalService: ModalService ,private userService: UserService){}
  @ViewChild('modal', { read: ViewContainerRef, static: true })
  entry!: ViewContainerRef;
  sub!: Subscription;

openModal() {
// MyComponent é o componente que será renderizado dentro do seu body
    this.sub = this.modalService
      .openModal(this.entry, 'Título do modal', SectionEmploymentRecordComponent)
      .subscribe((v) => {
        // dispara quando é aberto o modal
      });
  }

  private userData: any;
  userPhoto: string = "";
  userName: string = "";
  userBirthday: string = "";
  userAge: number = 0;
  userEmail: string = "";

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if(userState){
      this.userData = userState;
    }

    this.userService.getUserInformation(this.userData.userId).subscribe (
      (response: any) => {
        this.userName = response.name;
        this.userAge = response.age;
        this.userBirthday = response.birthday;
        this.userPhoto = response.photoUrl;
        this.userEmail = response.email;
      }
    );
  }

}
