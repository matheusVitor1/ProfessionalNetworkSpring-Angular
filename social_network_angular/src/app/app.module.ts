import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {NgxMaskModule,IConfig} from 'ngx-mask';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; // Importe o HttpClientModule
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderLoggedOutComponent } from './header-logged-out/header-logged-out.component';
import { PageLoginComponent } from './page-login/page-login.component';
import { PageUserCadastroComponent } from './page-user-register/page-user-cadastro.component';
import { HeaderLoggedInComponent } from './header-logged-in/header-logged-in.component';
import { PageUserSettingsComponent } from './page-user-settings/page-user-settings.component';
import { SectionHeroComponent } from './page-user-settings/section-hero/section-hero.component';
import { SectionAboutComponent } from './page-user-settings/section-about/section-about.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SectionEmploymentRecordComponent } from './page-user-settings/section-employment-record/section-employment-record.component';
import { AboutModalComponent } from './page-user-settings/section-about/about-modal/about-modal.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { HeroModalComponent } from './page-user-settings/section-hero/hero-modal/hero-modal.component';
import { AddRecordModalComponent } from './page-user-settings/section-employment-record/add-record-modal/add-record-modal.component';
import { EditRecordModalComponent } from './page-user-settings/section-employment-record/edit-record-modal/edit-record-modal.component';
import { FooterComponent } from './footer/footer.component';
import { SectionEducationalBackgroundComponent } from './page-user-settings/section-educational-background/section-educational-background.component';
import { EditEducationalBakgroundModalComponent } from './page-user-settings/section-educational-background/edit-educational-bakground-modal/edit-educational-bakground-modal.component';
import { AddEducationalBakgroundModalComponent } from './page-user-settings/section-educational-background/add-educational-bakground-modal/add-educational-bakground-modal.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderLoggedOutComponent,
    PageLoginComponent,
    PageUserCadastroComponent,
    HeaderLoggedInComponent,
    PageUserSettingsComponent,
    SectionHeroComponent,
    SectionAboutComponent,
    SectionEmploymentRecordComponent,
    AboutModalComponent,
    HeroModalComponent,
    AddRecordModalComponent,
    EditRecordModalComponent,
    FooterComponent,
    SectionEducationalBackgroundComponent,
    EditEducationalBakgroundModalComponent,
    AddEducationalBakgroundModalComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxMaskModule.forRoot({
      dropSpecialCharacters: false
    }),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
