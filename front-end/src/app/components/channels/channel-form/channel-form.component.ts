import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { ChannelService } from '../../../services/channel.service';
import { Channel } from '../../../models/channel';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-channel-form',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './channel-form.component.html',
  styleUrl: './channel-form.component.css'
})
export class ChannelFormComponent {

  currentRoute = inject(ActivatedRoute);
  action = this.currentRoute.snapshot.paramMap.get('action');
  id = this.currentRoute.snapshot.paramMap.get('id');

  channelService = inject(ChannelService);
  channel: Channel = new Channel();

  errorMessage!: string;
  successMessage!: string;

  constructor() {
    if (this.action != null) {
      if (this.action == 'put' || this.action == 'delete') {
        if (this.id != null) {
          this.getChannel();
        }
      }
    }
  }

  postChannel() {
    this.channelService.post(this.channel).subscribe({
      next: (response) => {
        this.channel = new Channel();
        this.errorMessage = "";
        this.successMessage = "Canal publicado!";
      },
      error: (response) => {
        this.errorMessage = response.error.name + '!';
      }
    })
  }

  getChannel() {
    if (this.id != null) {
      this.channelService.getById(this.id).subscribe({
        next: (returnedChannel) => {
          this.channel = returnedChannel;
        },
        error: (response) => {
          console.log("Erro ao obter o canal!");
        }
      })
    }
  }

  putChannel() {
    this.channelService.put(this.channel).subscribe({
      next: (response) => {
        this.errorMessage = "";
        this.successMessage = "Canal editado!";
      },
      error: (response) => {
        this.errorMessage = response.error.name + '!';
      }
    })
  }

  deleteChannel() {
    if (this.id != null) {
      this.channelService.deleteById(this.id).subscribe({
        next: (response) => {
          this.channel = new Channel();
          this.errorMessage = "";
          this.successMessage = "Canal excluído!";
        },
        error: (response) => {
          console.log(response);
          this.errorMessage = response.error.message + '!';
        }
      })
    }
  }
  
}
