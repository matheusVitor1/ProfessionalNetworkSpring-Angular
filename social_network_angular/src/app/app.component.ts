import { Component, OnInit, HostListener  } from '@angular/core';
import { UserService } from './services/user.service';
import { CanLoad, Route, UrlSegment, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'social.network.app';
  private userData: any;
  isAuthenticated: boolean = false;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    const userState = this.userService.localStorageGetUserInfo();
    if (userState) {
      this.userData = userState;
      this.isAuthenticated = true; // Defina como true se houver um usuário no estado
    }

    this.userService.isAuthenticated$.subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });
  }

  @HostListener('window:beforeunload', ['$event'])
  beforeUnloadHandler(event: any) {
    // Verificar se o evento de fechamento é explícito (ao fechar a guia ou o navegador)
    if (event instanceof Event) {
      // Verificar se a página está sendo recarregada
      if (!this.isPageReloading()) {
        // Limpar o Local Storage ou realizar outras ações antes de fechar o navegador
        this.userService.logoutUser();
      }
    }
  }

  private isPageReloading(): boolean {
    // Verificar se a página está sendo recarregada
    return performance && performance.navigation && performance.navigation.type === 1;
  }


  // canLoad(
  //   route: Route,
  //   segments: UrlSegment[]
  // ): Observable<boolean> | Promise<boolean> | boolean {
  //   if (this.isAuthenticated) {
  //     // Usuário autenticado, permite o carregamento do módulo
  //     return true;
  //   }

  //   // Usuário não autenticado, redireciona para a página de login
  //   this.router.navigate(['/login']);
  //   return false;
  // }
}
