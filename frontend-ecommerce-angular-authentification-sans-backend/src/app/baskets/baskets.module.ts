import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BasketsRoutingModule } from './baskets-routing.module';
import { EditBasketComponent } from './pages/edit-basket/edit-basket.component';
import { AddBasketComponent } from './pages/add-basket/add-basket.component';
import { ListBasketsComponent } from './pages/list-baskets/list-baskets.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PipesModule } from '../pipes/pipes.module';

@NgModule({
  declarations: [
    EditBasketComponent,
    AddBasketComponent,
    ListBasketsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    BasketsRoutingModule,
    PipesModule
  ],
  exports: [
    EditBasketComponent,
    AddBasketComponent,
    ListBasketsComponent
  ]
})
export class BasketsModule { }
