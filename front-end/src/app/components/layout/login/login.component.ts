import { Component, inject } from '@angular/core';
import { Login } from '../../../models/login';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { GenericResponse } from '../../../models/generic-response';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  login: Login = new Login();
  loginService = inject(LoginService);

  constructor() {
    localStorage.removeItem("token");
  }

  signIn() {

    this.loginService.signIn(this.login).subscribe({
      next: (GenericResponse) => {
        localStorage.setItem("token", GenericResponse.response);
      },
      error: (GenericResponse) => {
        window.alert("Usuário ou senha incorretos!")
      }
    })
  }

}
