import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EditBasketItemComponent } from './basket-items/pages/edit-basket-item/edit-basket-item.component';
import { AddBasketItemComponent } from './basket-items/pages/add-basket-item/add-basket-item.component';
import { ListBasketItemsComponent } from './basket-items/pages/list-basket-items/list-basket-items.component';
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

@NgModule({
  declarations: [
    AppComponent,
    ReplacePipe,
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
