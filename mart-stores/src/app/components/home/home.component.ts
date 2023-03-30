import { Component, OnInit } from '@angular/core';
import { ProductStatus } from 'src/app/model/product-status';
import { ProductStatusService } from 'src/app/services/product-status.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  
 ngOnInit(): void {
  
 }
  
 images =[
  {src:"assets/images/back7.jpg"},
  {src:"assets/images/back2.jpg"},
  {src:"assets/images/back3.jpg"},
  {src:"assets/images/back6.jpg"},
 ]



}
