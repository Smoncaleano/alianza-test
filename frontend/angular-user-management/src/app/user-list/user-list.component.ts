import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import * as XLSX from 'xlsx';
import { UserPopupData } from '../models/user-popup-data';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserFormModalComponent } from '../user-form-modal/user-form-modal.component';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  sharedKey!: string;
  userList: UserPopupData[] = [];

  constructor(
    private userService: UserService,

    private modalService: NgbModal,
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  exportUsers(): void {
    const exportData = this.userList.map(user => {
      return {
        'Shared Key': user.sharedKey,
        'Business ID': user.businessID,
        'E-mail': user.email,
        'Phone': user.phone,
        'Date Added': user.startDate
      };
    });

    const ws: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData);
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Users');

    XLSX.writeFile(wb, 'users.xlsx');
  }

  openPopup(action: string): void {


      if (action === 'new') {
        const modalRef = this.modalService.open(UserFormModalComponent, { centered: true });
    
        const newUserData: UserPopupData = {
          sharedKey: '',
          businessID: '',
          email: '',
          phone: '',
          startDate: new Date(),
          endDate: new Date()
        };
    
        modalRef.componentInstance.userData = newUserData;

        modalRef.result.then((result) => {
          if (result === 'saved') {
  
            this.getAllUsers();
          }
        }).catch((reason) => {

          console.log(`Modal closed with: ${reason}`);
        });
   
    }
  }

  searchUser(): void {
    if (this.sharedKey) {
      this.userService.searchUserBySharedKey(this.sharedKey).subscribe(
        (data) => {
          this.userList = [data]; 
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
