import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionEmploymentRecordComponent } from './section-employment-record.component';

describe('SectionEmploymentRecordComponent', () => {
  let component: SectionEmploymentRecordComponent;
  let fixture: ComponentFixture<SectionEmploymentRecordComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectionEmploymentRecordComponent]
    });
    fixture = TestBed.createComponent(SectionEmploymentRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
