import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionHeroProfileComponent } from './section-hero-profile.component';

describe('SectionHeroProfileComponent', () => {
  let component: SectionHeroProfileComponent;
  let fixture: ComponentFixture<SectionHeroProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectionHeroProfileComponent]
    });
    fixture = TestBed.createComponent(SectionHeroProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
