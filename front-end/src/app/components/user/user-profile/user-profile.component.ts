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

  signUp() {

    this.userService.signUp(this.newUser).subscribe({
      next: (response) => {
        this.sucessMessage = "Colaborador cadastrado com sucesso!";
        this.errorMessage = "";
        this.newUser = new UserRegister();
        this.comparisonPassword = "";
      },
      error: (reponse) => {
        this.errorMessage = reponse.error.message;
      }
    })
  }

}
