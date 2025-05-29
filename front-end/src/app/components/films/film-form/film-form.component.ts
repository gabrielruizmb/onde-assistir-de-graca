import { Component, inject } from '@angular/core';
import { Film } from '../../../models/film';
import { HeaderComponent } from '../../layout/header/header.component';
import { CategoryService } from '../../../services/category.service';
import { Category } from '../../../models/category';
import { FormsModule } from '@angular/forms';
import { ChannelService } from '../../../services/channel.service';
import { Channel } from '../../../models/channel';
import { FilmService } from '../../../services/film.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user';

@Component({
  selector: 'app-film-form',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './film-form.component.html',
  styleUrl: './film-form.component.css'
})
export class FilmFormComponent {

  currentRoute = inject(ActivatedRoute);
  action = this.currentRoute.snapshot.paramMap.get('action');
  filmId = this.currentRoute.snapshot.paramMap.get('id');

  film: Film = new Film();

  titleMessage!: string;
  errorMessage!: string;
  successMessage!: string;

  filmService = inject(FilmService);
  categoryService = inject(CategoryService);
  channelService = inject(ChannelService);
  userService = inject(UserService);
  myRouter = inject(Router);
  
  categoriesList: Category[] = [];
  channelsList: Channel[] = [];
  
  currentUser!: User;
  
  constructor() {
    this.getAllCategories();
    this.getAllChannels();

    if (this.action == "put" || this.action == "delete") {
      
      !this.userService.getToken() ?
        this.myRouter.navigate(['/login']) :
        this.currentUser = this.userService.getCurrentUser();

      if (this.filmId != null) {
        this.filmService.getFilm(this.filmId).subscribe({
          next: (returnedFilm) => {
            this.film = returnedFilm;

            if (
              !this.currentUser.roles.includes("ROLE_ADMIN") && 
              this.film.createdBy != this.currentUser.id
            ) {
              this.myRouter.navigate(['/login']);
            }

            if (this.action == "put")
              this.film.channels = [];

            console.log(this.film);

          },
          error: (error) => {
            console.log(error);
          }
        })
      }

    }
  }

  getAllCategories() {
    this.categoryService.getAll().subscribe({
      next: (returnedCategories) => {
        this.categoriesList = returnedCategories;
      },
      error: (error) => {
        console.log("Falha ao buscar a lista de categorias.");
      }
    })
  }

  getAllChannels() {
    this.channelService.getAll().subscribe({
      next: (returnedChannels) => {
        this.channelsList = returnedChannels;
      },
      error: (error) => {
        console.log("Não foi possível obter a lista de canais.");
      }
    })
  }

  changeFilmCategory(category: Category) {
    this.film.category = category;
  }

  changeFilmChannels(choicedChannel: Channel) {

    let selectedChannel = document.getElementById(choicedChannel.id);

    if (selectedChannel != null) {      

      if (!this.film.channels.includes(choicedChannel)) {
        this.film.channels.push(choicedChannel);

        // Pinta o fundo dos canais com uma cor de contraste.
        selectedChannel.style.backgroundColor = "rgb(8, 8, 8)";
      } else {
        this.film.channels = this.film.channels
          .filter(channel => channel.id != choicedChannel.id);

        // Retira a cor de fundo com contraste.
        selectedChannel.style.backgroundColor = "rgb(20, 19, 19)";
      }

      console.log(this.film.channels);
    }

  }
  
  postFilm() {

    this.film.createdBy = this.userService.getCurrentUser().id;

    this.filmService.postFilm(this.film).subscribe({
      next: (response) => {
        console.log(response);
        this.titleMessage = "";
        this.errorMessage = "";
        this.successMessage = "Filme postado!";
        this.film = new Film();
      },
      error: (response) => {
        console.log(response);
        this.successMessage = "";
        this.titleMessage = response.error.title;
        this.errorMessage = response.error.channels;
      }
    });

  }

  putFilm() {

    this.filmService.putFilm(this.film, this.film.id).subscribe({
      next: (response) => {
        console.log(response);
        this.titleMessage = "";
        this.errorMessage = "";
        this.successMessage = "Filme editado!";
        this.film = new Film();
      },
      error: (response) => {
        console.log(response);
        this.successMessage = "";
        this.titleMessage = response.error.title;
        this.errorMessage = response.error.channels;
      }
    });
  }

  deleteFilm() {

    this.filmService.deleteFilm(this.film.id).subscribe({
      next: (response) => {
        this.film = new Film();
        this.successMessage = "Filme excluído!";
      },
      error: (response) => {
        this.errorMessage = "Este filme já foi excluído!";
      }

    })
  }
}
