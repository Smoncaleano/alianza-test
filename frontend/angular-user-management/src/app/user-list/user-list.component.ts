import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  sharedKey!: string;
  userList!: any[]; // Deberías definir la interfaz adecuada para tus datos de usuario

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  openPopup(action: string): void {
    // Lógica para abrir el popup
  }

  searchUser(): void {
    if (this.sharedKey) {
      this.userService.searchUserBySharedKey(this.sharedKey).subscribe(
        (data) => {
          this.userList = [data]; // Coloca el resultado en un array para iterar en el ngFor
        },
        (error) => {
          console.error(error);
        }
      );
    } else {
      this.getAllUsers();
    }
  }

  getAllUsers(): void {
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.userList = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
