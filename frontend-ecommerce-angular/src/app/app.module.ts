import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EditClientComponent } from './clients/pages/edit-client/edit-client.component';
import { AddClientComponent } from './clients/pages/add-client/add-client.component';
import { ListClientsComponent } from './clients/pages/list-clients/list-clients.component';
import { ListProductsComponent } from './products/pages/list-products/list-products.component';
import { AddProductComponent } from './products/pages/add-product/add-product.component';
import { EditProductComponent } from './products/pages/edit-product/edit-product.component';
import { ListOrdersComponent } from './orders/pages/list-orders/list-orders.component';
import { AddOrderComponent } from './orders/pages/add-order/add-order.component';
import { EditOrderComponent } from './orders/pages/edit-order/edit-order.component';
import { EditBasketItemComponent } from './basket-items/pages/edit-basket-item/edit-basket-item.component';
import { AddBasketItemComponent } from './basket-items/pages/add-basket-item/add-basket-item.component';
import { ListBasketItemsComponent } from './basket-items/pages/list-basket-items/list-basket-items.component';
import { EditBasketComponent } from './baskets/pages/edit-basket/edit-basket.component';
import { AddBasketComponent } from './baskets/pages/add-basket/add-basket.component';
import { ListBasketsComponent } from './baskets/pages/list-baskets/list-baskets.component';
import { EditCategorieComponent } from './categories/pages/edit-categorie/edit-categorie.component';
import { AddCategorieComponent } from './categories/pages/add-categorie/add-categorie.component';
import { ListCategoriesComponent } from './categories/pages/list-categories/list-categories.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ReplacePipe } from './pipes/replace.pipe';
import { SpaceSeparatedPipe } from './pipes/space-separated.pipe';

@NgModule({
  declarations: [
    AppComponent,
    EditClientComponent,
    AddClientComponent,
    ListClientsComponent,
    ListProductsComponent,
    AddProductComponent,
    EditProductComponent,
    ListOrdersComponent,
    AddOrderComponent,
    EditOrderComponent,
    EditBasketItemComponent,
    AddBasketItemComponent,
    ListBasketItemsComponent,
    EditBasketComponent,
    AddBasketComponent,
    ListBasketsComponent,
    EditCategorieComponent,
    AddCategorieComponent,
    ListCategoriesComponent,
    HeaderComponent,
    ReplacePipe,
    SpaceSeparatedPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
