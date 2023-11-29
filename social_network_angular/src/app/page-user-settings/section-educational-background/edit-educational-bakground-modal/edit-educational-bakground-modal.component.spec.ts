import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEducationalBakgroundModalComponent } from './edit-educational-bakground-modal.component';

describe('EditEducationalBakgroundModalComponent', () => {
  let component: EditEducationalBakgroundModalComponent;
  let fixture: ComponentFixture<EditEducationalBakgroundModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditEducationalBakgroundModalComponent]
    });
    fixture = TestBed.createComponent(EditEducationalBakgroundModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
