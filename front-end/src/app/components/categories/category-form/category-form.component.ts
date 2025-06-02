import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { FormsModule } from '@angular/forms';
import { Category } from '../../../models/category';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-category-form',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './category-form.component.html',
  styleUrl: './category-form.component.css'
})
export class CategoryFormComponent {

  currentRoute = inject(ActivatedRoute);
  action = this.currentRoute.snapshot.paramMap.get('action');
  id = this.currentRoute.snapshot.paramMap.get('id');

  category: Category = new Category();
  categoryService = inject(CategoryService);

  errorMessage!: string;
  successMessage!: string;

  constructor() {

  }

  postCategory() {
    this.categoryService.postCategory(this.category).subscribe({
      next: (response) => {
        this.errorMessage = "";
        this.category = new Category();
        this.successMessage = "Categoria publicada!"
      },
      error: (response) => {
        console.log(response);
        this.errorMessage = response.error.nome;
      }
    })
  }

  putCategory() {

  }

  deleteCategory() {

  }
}
