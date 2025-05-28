import { Component, inject } from '@angular/core';
import { Film } from '../../../models/film';
import { HeaderComponent } from '../../layout/header/header.component';
import { CategoryService } from '../../../services/category.service';
import { Category } from '../../../models/category';
import { FormsModule } from '@angular/forms';
import { ChannelService } from '../../../services/channel.service';
import { Channel } from '../../../models/channel';
import { FilmService } from '../../../services/film.service';

@Component({
  selector: 'app-film-form',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './film-form.component.html',
  styleUrl: './film-form.component.css'
})
export class FilmFormComponent {

  film: Film = new Film();
  titleMessage!: string;
  errorMessage!: string;
  successMessage!: string;

  filmService = inject(FilmService);
  categoryService = inject(CategoryService);
  channelService = inject(ChannelService);

  categoriesList: Category[] = [];
  channelsList: Channel[] = [];

  constructor() {
    this.getAllCategories();
    this.getAllChannels();
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
        selectedChannel.style.backgroundColor = "rgb(8, 8, 8)";
      } else {
        this.film.channels = this.film.channels
          .filter(channel => channel.id != choicedChannel.id);

        selectedChannel.style.backgroundColor = "rgb(20, 19, 19)";
      }

      console.log(this.film.channels);
    }

  }
  
  postFilm() {
    console.log(this.film);

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
    })
  }
}
