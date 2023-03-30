import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItems } from 'src/app/model/cart-items';
import { CartProduct } from 'src/app/model/cart-product';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  cartProduct: CartProduct[] = [];
  sumPrices:number = 0;
  constructor(private route: Router, private cartService: CartService,  private authService: AuthService) {}

  ngOnInit(): void {
    this.getCartItemsById();
  }
  backToHome() {
    this.route.navigateByUrl('/');
  }

  getCartItemsById() {
    this.authService.getData().subscribe(
      data=>{
         const userid = data.id;
         this.cartService.getCartItemsById(userid).subscribe((data) => {
          this.cartProduct = data;
          for (const i of this.cartProduct) {
             this.sumPrices += i.productPrice;  
          }
        });
      }
    )
  }

  deleteByProduct(Cartitems:CartProduct){
    this.cartService.deleteById(Cartitems.productId,Cartitems.id).subscribe(
      data=>{
        this.route.navigateByUrl('/cart');
        this.getCartItemsById()
      }
    )
  }
}
