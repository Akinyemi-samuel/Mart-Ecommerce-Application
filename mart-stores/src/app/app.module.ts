import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderNavComponent } from './components/header-nav/header-nav.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductStatusService } from './services/product-status.service';
import { ProductStatusMenuComponent } from './components/product-status-menu/product-status-menu.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { SearchComponent } from './search/search.component';
import { ProductTempListComponent } from './components/product-temp-list/product-temp-list.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { UserService } from './services/user.service';
import { AuthService } from './services/auth.service';
import { CartService } from './services/cart.service';
import { CartComponent } from './components/cart/cart.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderNavComponent,
    CarouselComponent,
    HomeComponent,
    AboutComponent,
    ProductListComponent,
    ProductStatusMenuComponent,
    SearchComponent,
    ProductTempListComponent,
    ProductDetailsComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    CartComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [ProductStatusService,UserService,AuthService,CartService],
  bootstrap: [AppComponent]
})
export class AppModule { }
