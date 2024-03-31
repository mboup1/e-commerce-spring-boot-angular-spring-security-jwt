import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListClientsComponent } from './clients/pages/list-clients/list-clients.component';
import { ListProductsComponent } from './products/pages/list-products/list-products.component';
import { ListOrdersComponent } from './orders/pages/list-orders/list-orders.component';
import { AddProductComponent } from './products/pages/add-product/add-product.component';
import { EditProductComponent } from './products/pages/edit-product/edit-product.component';

const routes: Routes = [
  { path: 'products', component: ListProductsComponent },
  { path: 'clients', component: ListClientsComponent },
  { path: 'orders', component: ListOrdersComponent },
  { path: 'addProduct', component: AddProductComponent },
  { path: 'edit-product/:id', component: EditProductComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
