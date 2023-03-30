import { Component, OnInit } from '@angular/core';
import { ProductStatus } from 'src/app/model/product-status';
import { ProductStatusService } from 'src/app/services/product-status.service';

@Component({
  selector: 'app-product-status-menu',
  templateUrl: './product-status-menu.component.html',
  styleUrls: ['./product-status-menu.component.scss'],
})
export class ProductStatusMenuComponent implements OnInit {
  productStatus: ProductStatus[] = [];

  constructor(private productstatuservice: ProductStatusService) {}
  
  ngOnInit(): void {
    this.getProductService();
  }

  public getProductService() {
    this.productstatuservice.getProductStatus().subscribe((data) => {
      this.productStatus = data;
    });
  }
}
