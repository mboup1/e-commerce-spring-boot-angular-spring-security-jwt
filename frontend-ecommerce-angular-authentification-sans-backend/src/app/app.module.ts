import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ReplacePipe } from './pipes/replace.pipe';
import { HeaderModule } from './header/header.module';
import { ClientsModule } from './clients/clients.module';
import { ProductsModule } from './products/products.module';
import { OrdersModule } from './orders/orders.module';
import { BasketsModule } from './baskets/baskets.module';
import { BasketItemsModule } from './basket-items/basket-items.module';
import { CategoriesModule } from './categories/categories.module';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './errors/forbidden/forbidden.component';

@NgModule({
  declarations: [
    AppComponent,
    ReplacePipe,
    LoginComponent,
    ForbiddenComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    ClientsModule,
    ProductsModule,
    OrdersModule,
    BasketsModule,
    BasketItemsModule,
    CategoriesModule,
    HeaderModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
