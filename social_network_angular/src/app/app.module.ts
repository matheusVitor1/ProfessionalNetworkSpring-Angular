import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderLoggedOutComponent } from './header-logged-out/header-logged-out.component';
import { PageLoginComponent } from './page-login/page-login.component';
import { PageUserCadastroComponent } from './page-user-cadastro/page-user-cadastro.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderLoggedOutComponent,
    PageLoginComponent,
    PageUserCadastroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
