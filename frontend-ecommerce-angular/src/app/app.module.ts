import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ListClientsComponent } from './clients/pages/list-clients/list-clients.component';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { AddClientComponent } from './clients/pages/add-client/add-client.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AddClientComponent // Ajoutez AddClientComponent ici si ce n'est pas déjà fait

    // ListClientsComponent
  ],


  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
