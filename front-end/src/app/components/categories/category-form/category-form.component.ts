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
    if (this.action == 'put' || this.action == 'delete') {
      this.getById();
    }
  }

  getById() {
    if(this.id != null) {
      this.categoryService.getById(this.id).subscribe({
        next: (returnedCategory) => {
          this.category = returnedCategory;
        },
        error: (response) => {
          console.log(response);
        }
      });
    }
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
    });
  }

  putCategory() {
    this.categoryService.putCategory(this.category).subscribe({
      next: (response) => {
        this.errorMessage = "";
        this.successMessage = "Categoria editada!"
      },
      error: (response) => {
        console.log(response);
        this.errorMessage = response.error.nome;
      }
    });
  }

  deleteCategory() {
    if (this.id != null) {
      this.categoryService.deleteCategory(this.id).subscribe({
        next: (response) => {
          this.errorMessage = "";
          this.category = new Category();
          this.successMessage = "Categoria excluída!"
        },
        error: (response) => {
          console.log(response);
          this.errorMessage = response.error.nome;
        }
      });
    }
  }
}
