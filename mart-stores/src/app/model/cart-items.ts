import { Product } from './product';

export class CartItems {
  userid!: String;
  productid!: Number;
  productName!: String;
  productImg!: String;
  productQuantity!: Number;
  productPrice!: Number;

  constructor(product: Product, id: string) {
    this.userid = id;
    this.productid = product.id;
    this.productName = product.name;
    this.productImg = product.imageUrl;
    this.productQuantity = 1;
    this.productPrice = product.unitPrice;
  }
}
