import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEdubackgroundModalComponent } from './edit-edubackground-modal.component';

describe('EditEdubackgroundModalComponent', () => {
  let component: EditEdubackgroundModalComponent;
  let fixture: ComponentFixture<EditEdubackgroundModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditEdubackgroundModalComponent]
    });
    fixture = TestBed.createComponent(EditEdubackgroundModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
