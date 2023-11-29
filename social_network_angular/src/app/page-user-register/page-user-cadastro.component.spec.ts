import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageUserCadastroComponent } from './page-user-cadastro.component';

describe('PageUserCadastroComponent', () => {
  let component: PageUserCadastroComponent;
  let fixture: ComponentFixture<PageUserCadastroComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageUserCadastroComponent]
    });
    fixture = TestBed.createComponent(PageUserCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
