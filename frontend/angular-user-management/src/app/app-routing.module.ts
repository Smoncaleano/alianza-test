import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'clients', component: UserListComponent },
  { path: 'home', component: HomeComponent },
  // Otras rutas según sea necesario
];




@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      useHash: true,
      onSameUrlNavigation: 'reload',
    }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule { }
