import { HttpClient } from '@angular/common/http';
import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  Renderer2,
} from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header-nav',
  templateUrl: './header-nav.component.html',
  styleUrls: ['./header-nav.component.scss'],
})
export class HeaderNavComponent implements OnInit {
  isUserActive!: String | null;
  userActive: boolean = false;
  activeName!: string;
  USERDETAILS: Subject<any> = new Subject<any>();
  togglevar: boolean = false;

  constructor(
    private authservice: AuthService,
    private http: HttpClient,
    private renderer: Renderer2,
    private elemref: ElementRef,
    private route:Router
  ) {}
  ngOnInit(): void {
    this.getData();
    this.isUserActive = this.authservice.getToken();
    if (this.isUserActive) {
      this.userActive = true;
    }
  }

  toggle() {
    this.togglevar = !this.togglevar;
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    // Handle scroll event here
    const nav = this.elemref.nativeElement.querySelector('.nav_header');
    if (window.pageYOffset > nav.offsetTop) {
      this.renderer.addClass(nav, 'fixed-top');
    } else {
      this.renderer.removeClass(nav, 'fixed-top');
    }
  }
  

  getData() {
    if (this.authservice.getToken != null) {
      this.authservice.getData().subscribe(
        (data) => {
          //get the fullname of user and save the lastname from where there is a space
          const stringname = data.fullName;
          this.USERDETAILS.next(data);
          const startIndex = stringname.indexOf(' ');
          this.activeName = stringname.substring(startIndex + 1);
        },
        (err) => {
          //this.authservice.logoutToken();
          console.log(err);
        }
      );
    } else {
    }
  }

  logout() {
    this.authservice.logoutToken();
    window.location.reload();
  }

  searchValue(value:string){
 this.route.navigateByUrl(`/search/${value}`)
  }

}
