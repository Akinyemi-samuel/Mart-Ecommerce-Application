import {
  Component,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-product-temp-list',
  templateUrl: './product-temp-list.component.html',
  styleUrls: ['./product-temp-list.component.scss'],
})
export class ProductTempListComponent {
  @Input() products: Product[] = [];
  @Input() size!: number;
  @Input() pageNumber!: number;
  @Input() totalElements!: number;

  @Output() emitFunctionOfParent: EventEmitter<any> = new EventEmitter<any>();

  onPageChange(number: number) {
    this.emitFunctionOfParent.emit(number);
  }
}
