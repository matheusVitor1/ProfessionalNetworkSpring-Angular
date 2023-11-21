import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter, take } from 'rxjs/operators';

@Component({
  selector: 'app-page-user-settings',
  templateUrl: './page-user-settings.component.html',
  styleUrls: ['./page-user-settings.component.css']
})
export class PageUserSettingsComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Observa o evento de navegação
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      take(1) // Garante que a ação só seja executada uma vez após a navegação inicial
    ).subscribe(() => {
      // Navega para a mesma rota, recarregando a página
      this.router.navigate([this.router.url]);
    });
  }
}
