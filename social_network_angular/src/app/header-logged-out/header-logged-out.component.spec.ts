import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderLoggedOutComponent } from './header-logged-out.component';

describe('HeaderLoggedOutComponent', () => {
  let component: HeaderLoggedOutComponent;
  let fixture: ComponentFixture<HeaderLoggedOutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderLoggedOutComponent]
    });
    fixture = TestBed.createComponent(HeaderLoggedOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
