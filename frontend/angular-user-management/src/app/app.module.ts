import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { UserListComponent } from './user-list/user-list.component';

import { HttpClientModule } from '@angular/common/http';


import { UserService } from './user.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserFormModalComponent } from './user-form-modal/user-form-modal.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [

    AppComponent,
    SidebarComponent,
    UserListComponent,
    UserFormModalComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, // Agrega FormsModule aquí si usas formularios template-driven
    ReactiveFormsModule, // Agrega FormsModule aquí
    AppRoutingModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [ UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }