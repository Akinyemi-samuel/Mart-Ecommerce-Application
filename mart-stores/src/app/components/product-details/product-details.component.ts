import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartItems } from 'src/app/model/cart-items';
import { Product } from 'src/app/model/product';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { ProductStatusService } from 'src/app/services/product-status.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss'],
})
export class ProductDetailsComponent implements OnInit {
  id!: number;
  product!: Product;
  category!: string;
  products: Product[] = [];
  LOG_CART_MSG!: String;

  constructor(
    private productservice: ProductStatusService,
    private activatedroute: ActivatedRoute,
    private cartService: CartService,
    private eleref: ElementRef,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(() => {
      this.getProductById();
    });
  }

 cartProduct(product:Product){

  this.authService.getData().subscribe((data)=>{
    const userid = data.id;
    if(product != null){
      const cartitem = new CartItems(product,userid)
      this.cartService.postToCart(cartitem).subscribe((data) => {
        const cartdiv = this.eleref.nativeElement.querySelector('.cartmsg');
        const btncart = this.eleref.nativeElement.querySelector('.button-sec');
        cartdiv.style.display = 'block';
        btncart.style.display = 'none';
        cartdiv.style.backgroundColor = 'green';
        cartdiv.style.color = 'white';
        cartdiv.style.padding = '10px';
        cartdiv.style.borderRadius = '5px';
        cartdiv.style.fontFamily = 'Poppins, sans-serif';
    
        this.LOG_CART_MSG = 'Item added to cart successfully';
        setTimeout(() => {
          const cartdiv = this.eleref.nativeElement.querySelector('.cartmsg');
          cartdiv.style.display = 'none';
          btncart.style.display = 'flex';
        }, 3000);
      });
    }
  },(err)=>{
    this.authService.logoutToken()
  })
  
 }

  getProductById() {
    const hasId = this.activatedroute.snapshot.paramMap.has('id');
    if (hasId) {
      this.id = +this.activatedroute.snapshot.paramMap.get('id')!;
    }

    return this.productservice.getProductById(this.id).subscribe((data) => {
      this.product = data;
      this.category = data.category.categoryName;

      this.productservice
        .findByCategoryIdRandomWithLimit(data.category.id)
        .subscribe((data) => {
          this.products = data;
        });
    });
  }


}
