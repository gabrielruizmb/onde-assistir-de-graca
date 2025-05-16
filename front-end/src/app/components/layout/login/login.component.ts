import { Component, inject } from '@angular/core';
import { Login } from '../../../models/login';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  login: Login = new Login();
  error!: string;

  router = inject(Router);
  userService = inject(UserService);

  constructor() {
    this.userService.removeToken(); 
  }

  signIn() {

    this.userService.signIn(this.login).subscribe({
      next: (response) => {
        this.userService.setToken(response.message);
        this.router.navigate(['home']);
      },
      error: (response) => {
        this.error = response.error.message + "!";
      }
    })
  }
}
