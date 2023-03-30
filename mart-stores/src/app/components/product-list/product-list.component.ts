import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { CartItems } from 'src/app/model/cart-items';
import { Product } from 'src/app/model/product';
import { ProductStatus } from 'src/app/model/product-status';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { ProductStatusService } from 'src/app/services/product-status.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  productStatusNumber: number = 1;
  size: number = 8;
  pageNumber: number = 1;
  totalElements: number = 0;
  products: Product[] = [];
  LOG_CART_MSG!: String;

  constructor(
    private productService: ProductStatusService,
    private activatedRoute: ActivatedRoute,
    private cartService: CartService,
    private eleref: ElementRef,
    private authservice: AuthService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(() => {
      this.pageNumber = 1;
      this.getProductByStatus();
    });
  }

  getProductByStatus() {
    const hasrouteId = this.activatedRoute.snapshot.paramMap.has('id');
    if (hasrouteId) {
      this.productStatusNumber =
        +this.activatedRoute.snapshot.paramMap.get('id')!;
    } else {
      this.productStatusNumber = 1;
    }

    this.productService
      .getProductByStatus(
        this.productStatusNumber,
        this.pageNumber - 1,
        this.size
      )
      .subscribe((data) => {
        this.products = data.content;
        this.pageNumber = data.number + 1;
        this.size = data.size;
        this.totalElements = data.totalElements;
      });
  }

  onPageChange(number: number) {
    this.pageNumber = number;
    this.getProductByStatus();
  }

  cartProduct(product: Product) {
    this.authservice.getData().subscribe(
      (data) => {
        //get the id of user and push to cart to save  product
        const userid = data.id;
        const cartitem = new CartItems(product, userid);

        this.cartService.postToCart(cartitem).subscribe((data) => {
          const cartdiv = this.eleref.nativeElement.querySelector('.cartmsg');
          cartdiv.style.display = 'block';
          cartdiv.style.backgroundColor = 'green';
          cartdiv.style.color = 'white';
          cartdiv.style.padding = '10px';
          cartdiv.style.fontFamily = 'Poppins, sans-serif';

          this.LOG_CART_MSG = 'Item added to cart successfully';
          setTimeout(() => {
            const cartdiv = this.eleref.nativeElement.querySelector('.cartmsg');
            cartdiv.style.display = 'none';
          }, 3000);
        });
      },
      (err) => {
        this.authservice.logoutToken();
      }
    );
  }
}
