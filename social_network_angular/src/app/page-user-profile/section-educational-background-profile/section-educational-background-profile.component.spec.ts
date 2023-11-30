import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionEducationalBackgroundProfileComponent } from './section-educational-background-profile.component';

describe('SectionEducationalBackgroundProfileComponent', () => {
  let component: SectionEducationalBackgroundProfileComponent;
  let fixture: ComponentFixture<SectionEducationalBackgroundProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectionEducationalBackgroundProfileComponent]
    });
    fixture = TestBed.createComponent(SectionEducationalBackgroundProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
