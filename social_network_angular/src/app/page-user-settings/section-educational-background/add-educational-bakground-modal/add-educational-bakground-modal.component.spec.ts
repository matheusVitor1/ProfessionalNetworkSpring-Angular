import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEducationalBakgroundModalComponent } from './add-educational-bakground-modal.component';

describe('AddEducationalBakgroundModalComponent', () => {
  let component: AddEducationalBakgroundModalComponent;
  let fixture: ComponentFixture<AddEducationalBakgroundModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddEducationalBakgroundModalComponent]
    });
    fixture = TestBed.createComponent(AddEducationalBakgroundModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
