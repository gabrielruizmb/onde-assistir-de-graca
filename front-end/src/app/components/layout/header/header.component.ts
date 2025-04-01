import { Component } from '@angular/core';
import { CategoriesListComponent } from '../../category/categories-list/categories-list.component';

@Component({
  selector: 'app-header',
  imports: [CategoriesListComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
