// user-form-modal.component.ts

import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserPopupData } from '../models/user-popup-data';
import { UserService } from '../user.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-user-form-modal',
  templateUrl: './user-form-modal.component.html',
  styleUrls: ['./user-form-modal.component.css']
})
export class UserFormModalComponent implements OnInit {
  @Input() userData: UserPopupData = new UserPopupData();
  userForm!: FormGroup;

  constructor(
    public activeModal: NgbActiveModal,
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {}

  showFieldError(fieldName: string): boolean {
    const fieldControl = this.userForm.get(fieldName);
    return !!fieldControl && !!fieldControl.invalid && (!!fieldControl.dirty || !!fieldControl.touched);
  }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      businessID: ['', [Validators.required, Validators.minLength(1)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern(/^\d*$/)]],
      startDate: [new Date().toISOString().split('T')[0], Validators.required],
      endDate: ['', Validators.required]
    });
  }

  closeModal(): void {
    this.activeModal.close('Closed');
  }

  saveUser(): void {
    if (this.userForm.valid) {
      const userFormData = this.userForm.value as UserPopupData;
      userFormData.startDate = new Date(userFormData.startDate); 

      this.userService.createNewUser(userFormData).subscribe(
        (response) => {
          console.log('Usuario creado exitosamente', response);
          this.activeModal.close('saved');
          this.showSuccessAlert();
        },
        (error) => {
          if (error != null) {
            console.error('Error al crear el usuario', error);
            this.showErrorAlert(error.error);
          }
        }
      );
    } else {
      console.log('Formulario inválido. Por favor, revise los campos.');
    }
  }


  shouldShowDateeNDError(): string | null {
    const startDateControl = this.userForm.get('startDate');
    const endDateControl = this.userForm.get('endDate');
  
    if (startDateControl && endDateControl && startDateControl.value) {
      if (startDateControl.value >= endDateControl.value) {
        return 'La fecha final debe ser después que la fecha incial';
      } 
    }
  
    return null;
  }

  shouldShowDateError(): string | null {
    const startDateControl = this.userForm.get('startDate');
    const endDateControl = this.userForm.get('endDate');
  
    // Obtener la fecha actual
    const currentDate = new Date();
  
    if (startDateControl && endDateControl && startDateControl.value) {
      if (startDateControl.value >= endDateControl.value) {
        return 'La fecha de inicio debe ser menor que la fecha final';
      } else if (startDateControl.value > currentDate.toISOString().split('T')[0]) {
        return 'La fecha de inicio no puede ser en el futuro';
      }else if (startDateControl.value < currentDate.toISOString().split('T')[0]) {
        return '';
      }
    }
  
    return null;
  }

  get emailControl() {
    return this.userForm.get('email');
  }

  get startDateControl() {
    return this.userForm.get('startDate');
  }

  get endDateControl() {
    return this.userForm.get('endDate');
  }

  showSuccessAlert(): void {
    swal.fire({
      icon: 'success',
      title: 'Éxito',
      text: 'Usuario creado exitosamente',
    });
  }

  showErrorAlert(message: string): void {
    swal.fire({
      icon: 'error',
      title: 'Error',
      text: message,
    });
  }
}
