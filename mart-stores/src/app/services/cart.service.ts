import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CartItems } from '../model/cart-items';
import { CartProduct } from '../model/cart-product';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http:HttpClient) { }


  postToCart(cartitems:CartItems){
    const URL = `${environment.apiUrl}/cart/addproduct`;
    return this.http.put(URL,cartitems);
  }

  getCartItemsById(userId:String){
    const URL = `${environment.apiUrl}/cart/cartitems/${userId}`
    return this.http.get<CartProduct[]>(URL);
  }

  deleteById(product_id:string,item_id:number){
    const URL = `${environment.apiUrl}/cart/${product_id}/${item_id}`
    return this.http.delete(URL)
  }
}
