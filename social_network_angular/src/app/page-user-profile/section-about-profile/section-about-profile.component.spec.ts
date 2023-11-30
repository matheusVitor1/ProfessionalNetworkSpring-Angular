import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionAboutProfileComponent } from './section-about-profile.component';

describe('SectionAboutProfileComponent', () => {
  let component: SectionAboutProfileComponent;
  let fixture: ComponentFixture<SectionAboutProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectionAboutProfileComponent]
    });
    fixture = TestBed.createComponent(SectionAboutProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
