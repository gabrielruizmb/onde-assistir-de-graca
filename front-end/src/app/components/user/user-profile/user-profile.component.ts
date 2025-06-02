import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { User } from '../../../models/user';
import { UserService } from '../../../services/user.service';
import { FormsModule } from '@angular/forms';
import { UserRegister } from '../../../models/user-register';

@Component({
  selector: 'app-user-profile',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {

  userService = inject(UserService);

  currentUser: User = this.userService.getCurrentUser();
  newUser: UserRegister = new UserRegister();

  comparisonPassword!: string;
  errorMessage!: string;
  sucessMessage!: string;

  users: User[] = [];

  constructor() {
    this.getAllUsers();
  }

  signUp() {

    this.userService.signUp(this.newUser).subscribe({
      next: (response) => {
        this.sucessMessage = "Colaborador cadastrado com sucesso!";
        this.errorMessage = "";
        this.newUser = new UserRegister();
        this.comparisonPassword = "";
      },
      error: (response) => {
        this.errorMessage = response.error.message;
      }
    })
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe({
      next: (returnedUsers) => {
        this.users = returnedUsers;
        console.log(this.users);
      },
      error: (response) => {
        console.log(response);
      }
    })
  }

}
