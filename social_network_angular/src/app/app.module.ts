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
import { PageUserCadastroComponent } from './page-user-cadastro/page-user-cadastro.component';
import { HeaderLoggedInComponent } from './header-logged-in/header-logged-in.component';
import { PageUserSettingsComponent } from './page-user-settings/page-user-settings.component';
import { SectionHeroComponent } from './page-user-settings/section-hero/section-hero.component';
import { SectionAboutComponent } from './page-user-settings/section-about/section-about.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderLoggedOutComponent,
    PageLoginComponent,
    PageUserCadastroComponent,
    HeaderLoggedInComponent,
    PageUserSettingsComponent,
    SectionHeroComponent,
    SectionAboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxMaskModule.forRoot({
      dropSpecialCharacters: false
    }),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
