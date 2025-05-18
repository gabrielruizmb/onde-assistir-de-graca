import { Component, inject } from '@angular/core';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  userService = inject(UserService);
}
