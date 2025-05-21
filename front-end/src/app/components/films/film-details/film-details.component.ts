import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FilmService } from '../../../services/film.service';
import { Film } from '../../../models/film';
import { HeaderComponent } from '../../layout/header/header.component';

@Component({
  selector: 'app-film-details',
  imports: [HeaderComponent],
  templateUrl: './film-details.component.html',
  styleUrl: './film-details.component.css'
})
export class FilmDetailsComponent {

  filmService = inject(FilmService);
  currentRoute = inject(ActivatedRoute);
  film: Film = new Film();
  id!: string;

  constructor() {
    let id = this.currentRoute.snapshot.paramMap.get('id');

    if (id !== null) {
      this.id = id;
      this.getFilm(this.id);
    }
  }

  getFilm(id: string) {
    this.filmService.getFilm(id).subscribe({
      next: (film) => {
        this.film = film;
        console.log(this.film);
      },
      error: (error) => {
        console.log("Filme não encontrado.");
      }
    })
  }
}
