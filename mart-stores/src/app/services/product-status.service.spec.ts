import { TestBed } from '@angular/core/testing';

import { ProductStatusService } from './product-status.service';

describe('ProductStatusService', () => {
  let service: ProductStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
