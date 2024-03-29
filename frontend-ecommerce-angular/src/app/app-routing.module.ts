import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListClientsComponent } from './clients/pages/list-clients/list-clients.component';
import { ListProductsComponent } from './products/pages/list-products/list-products.component';
import { ListOrdersComponent } from './orders/pages/list-orders/list-orders.component';
import { AddClientComponent } from './clients/pages/add-client/add-client.component';

const routes: Routes = [
  { path: 'clients', component: ListClientsComponent },
  { path: 'products', component: ListProductsComponent },
  { path: 'orders', component: ListOrdersComponent },
  { path: 'addClient', component: AddClientComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
