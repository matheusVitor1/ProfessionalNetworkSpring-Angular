import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageUserSettingsComponent } from './page-user-settings.component';

describe('PageUserSettingsComponent', () => {
  let component: PageUserSettingsComponent;
  let fixture: ComponentFixture<PageUserSettingsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageUserSettingsComponent]
    });
    fixture = TestBed.createComponent(PageUserSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
