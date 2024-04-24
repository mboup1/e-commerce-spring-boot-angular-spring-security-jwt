import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { ListBasketsComponent } from './pages/list-baskets/list-baskets.component';

const routes: Routes = [
  { path: 'basket', component: ListBasketsComponent },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class BasketsRoutingModule { }
