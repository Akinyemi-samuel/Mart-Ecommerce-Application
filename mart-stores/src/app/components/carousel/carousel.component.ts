import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss'],
})
export class CarouselComponent implements OnInit {
  @Input() images: carouselImages[] = [];
  selectedIndex = 0;

  ngOnInit(): void {}

  currentSlide(index: number) {
    this.selectedIndex = index;
  }

  plusSlides(index: number) {
    this.selectedIndex = this.selectedIndex + index;
    if (this.selectedIndex == 4) {
      this.selectedIndex = 0;
    }
  }
}

interface carouselImages {
  src: string;
}
