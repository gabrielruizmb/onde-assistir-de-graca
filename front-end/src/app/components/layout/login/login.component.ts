import { Component, inject } from '@angular/core';
import { Login } from '../../../models/login';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  login: Login = new Login();
  loginService = inject(LoginService);

  signIn() {
    // window.alert(this.login.email);
    // window.alert(this.login.password);

    console.log(this.loginService.signIn(this.login));
  }
}
