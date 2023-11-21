import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRecordModalComponent } from './add-record-modal.component';

describe('AddRecordModalComponent', () => {
  let component: AddRecordModalComponent;
  let fixture: ComponentFixture<AddRecordModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddRecordModalComponent]
    });
    fixture = TestBed.createComponent(AddRecordModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
