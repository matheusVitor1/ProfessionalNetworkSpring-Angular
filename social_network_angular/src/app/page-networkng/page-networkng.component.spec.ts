import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNetworkngComponent } from './page-networkng.component';

describe('PageNetworkngComponent', () => {
  let component: PageNetworkngComponent;
  let fixture: ComponentFixture<PageNetworkngComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageNetworkngComponent]
    });
    fixture = TestBed.createComponent(PageNetworkngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
