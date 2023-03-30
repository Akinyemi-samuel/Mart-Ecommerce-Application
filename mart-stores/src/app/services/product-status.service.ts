import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';
import { ProductStatus } from '../model/product-status';

@Injectable({
  providedIn: 'root',
})
export class ProductStatusService {
  constructor(private http: HttpClient) {}

  public getProductStatus() {
    const URL = `${environment.apiUrl}/productstatus`;
    return this.http.get<ProductStatus[]>(URL);
  }

  public getProductByStatus(id: number, number: number, size: number) {
    const URL = `${environment.apiUrl}/product/bystatus/${id}/${number}/${size}`;
    return this.http.get<getResponse>(URL);
  }

  public getProductById(id: number) {
    const URL = `${environment.apiUrl}/product/${id}`;
    return this.http.get<Product>(URL);
  }

  public findByCategoryIdRandomWithLimit(id: number) {
    const URL = `${environment.apiUrl}/product/bycategory/random/${id}`;
    return this.http.get<Product[]>(URL);
  }

  searchProduct(name: string, number: number, size: number) {
    const URL = `${environment.apiUrl}/product/search/${name}/${number}/${size}`;
    return this.http.get<getResponse>(URL);
  }

}

interface getResponse {
  content: Product[];

  size: number;
  number: number;
  totalElements: number;
}
