import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageLoginComponent } from './page-login/page-login.component';
import { PageUserCadastroComponent } from './page-user-register/page-user-cadastro.component';
import { PageUserSettingsComponent } from './page-user-settings/page-user-settings.component';
import { PageNetworkngComponent } from './page-networkng/page-networkng.component';
const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch:'full'},
  {path: 'login', component: PageLoginComponent},
  {path: 'cadastroCliente', component: PageUserCadastroComponent},
  {path: 'userSettings', component: PageUserSettingsComponent},
  {path: 'network', component: PageNetworkngComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
