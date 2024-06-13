import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QrAuthComponent } from './qr-auth.component';

describe('QrAuthComponent', () => {
  let component: QrAuthComponent;
  let fixture: ComponentFixture<QrAuthComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QrAuthComponent]
    });
    fixture = TestBed.createComponent(QrAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
