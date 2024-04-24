import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { ListBasketItemsComponent } from './pages/list-basket-items/list-basket-items.component';

const routes: Routes = [
  { path: 'basket-items', component: ListBasketItemsComponent },

];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class BasketItemsRoutingModule { }
