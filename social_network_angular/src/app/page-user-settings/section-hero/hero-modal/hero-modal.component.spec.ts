import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroModalComponent } from './hero-modal.component';

describe('HeroModalComponent', () => {
  let component: HeroModalComponent;
  let fixture: ComponentFixture<HeroModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeroModalComponent]
    });
    fixture = TestBed.createComponent(HeroModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
