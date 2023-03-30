import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/product';
import { ProductStatusService } from '../services/product-status.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit {
  product: Product[] = [];
  size: number = 8;
  pageNumber: number = 1;
  totalElements: number = 0;
  keywordget!: string;
  constructor(
    private route: Router,
    private productservice: ProductStatusService,
    private activatedroute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(() => {
      this.searchProduct();
    });
  }

  backToHome() {
    this.route.navigateByUrl('/');
  }

  searchValue(value: string) {
    this.route.navigateByUrl(`/search/${value}`);
  }

  searchProduct() {
    const hasKeyword = this.activatedroute.snapshot.paramMap.has('keyword');

    if (hasKeyword) {
      this.keywordget = this.activatedroute.snapshot.paramMap.get('keyword')!;

      this.productservice
        .searchProduct(this.keywordget, this.pageNumber - 1, this.size)
        .subscribe((data) => {
          this.product = data.content;
          this.size = data.size;
          this.pageNumber = data.number + 1;
          this.totalElements = data.totalElements;
          // console.log(this.product);
        });
    }
  }

  onPageChange(event: number) {
    this.pageNumber = event;
    this.searchProduct();
  }
}
