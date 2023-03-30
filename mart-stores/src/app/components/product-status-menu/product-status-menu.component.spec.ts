import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductStatusMenuComponent } from './product-status-menu.component';

describe('ProductStatusMenuComponent', () => {
  let component: ProductStatusMenuComponent;
  let fixture: ComponentFixture<ProductStatusMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductStatusMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductStatusMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
