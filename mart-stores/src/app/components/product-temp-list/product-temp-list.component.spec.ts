import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductTempListComponent } from './product-temp-list.component';

describe('ProductTempListComponent', () => {
  let component: ProductTempListComponent;
  let fixture: ComponentFixture<ProductTempListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductTempListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductTempListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
