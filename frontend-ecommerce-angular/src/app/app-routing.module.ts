import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListClientsComponent } from './clients/pages/list-clients/list-clients.component';
import { ListProductsComponent } from './products/pages/list-products/list-products.component';

const routes: Routes = [
  { path: 'clients', component: ListClientsComponent },
  { path: 'products', component: ListProductsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
