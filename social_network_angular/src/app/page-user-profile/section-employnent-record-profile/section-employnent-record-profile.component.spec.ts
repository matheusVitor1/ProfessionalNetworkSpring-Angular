import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SectionEmploynentRecordProfileComponent } from './section-employnent-record-profile.component';

describe('SectionEmploynentRecordProfileComponent', () => {
  let component: SectionEmploynentRecordProfileComponent;
  let fixture: ComponentFixture<SectionEmploynentRecordProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SectionEmploynentRecordProfileComponent]
    });
    fixture = TestBed.createComponent(SectionEmploynentRecordProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
