import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBasketComponent } from './edit-basket.component';

describe('EditBasketComponent', () => {
  let component: EditBasketComponent;
  let fixture: ComponentFixture<EditBasketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditBasketComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditBasketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
