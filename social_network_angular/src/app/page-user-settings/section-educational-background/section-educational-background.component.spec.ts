import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionEducationalBackgroundComponent } from './section-educational-background.component';

describe('SectionEducationalBackgroundComponent', () => {
  let component: SectionEducationalBackgroundComponent;
  let fixture: ComponentFixture<SectionEducationalBackgroundComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectionEducationalBackgroundComponent]
    });
    fixture = TestBed.createComponent(SectionEducationalBackgroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
