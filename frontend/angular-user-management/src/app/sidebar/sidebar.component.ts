// sidebar.component.ts

import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  sidebarActive = true;

  constructor(private router: Router) { }
  
  isActiveRoute(route: string): boolean {
    return this.router.isActive(route, true);
  }
  

}
