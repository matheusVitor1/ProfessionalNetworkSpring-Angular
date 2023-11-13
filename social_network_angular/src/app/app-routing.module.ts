import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageLoginComponent } from './page-login/page-login.component';
import { PageUserCadastroComponent } from './page-user-cadastro/page-user-cadastro.component';
import { PageUserSettingsComponent } from './page-user-settings/page-user-settings.component';
import { AppComponent } from './app.component';
const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch:'full'},
  {path: 'login', component: PageLoginComponent},
  {path: 'cadastroCliente', component: PageUserCadastroComponent},
  {path: 'userSettings', component: PageUserSettingsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
