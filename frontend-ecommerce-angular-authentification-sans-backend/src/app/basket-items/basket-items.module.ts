import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BasketItemsRoutingModule } from './basket-items-routing.module';

import { EditBasketItemComponent } from './pages/edit-basket-item/edit-basket-item.component';
import { AddBasketItemComponent } from './pages/add-basket-item/add-basket-item.component';
import { ListBasketItemsComponent } from './pages/list-basket-items/list-basket-items.component';

@NgModule({
  declarations: [
    EditBasketItemComponent,
    AddBasketItemComponent,
    ListBasketItemsComponent
  ],
  imports: [
    CommonModule,
    BasketItemsRoutingModule
  ],
  exports: [
    EditBasketItemComponent,
    AddBasketItemComponent,
    ListBasketItemsComponent
  ]
})
export class BasketItemsModule { }
