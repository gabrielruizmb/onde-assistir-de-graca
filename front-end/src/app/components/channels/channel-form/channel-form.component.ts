import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../../layout/header/header.component';
import { ChannelService } from '../../../services/channel.service';
import { Channel } from '../../../models/channel';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-channel-form',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './channel-form.component.html',
  styleUrl: './channel-form.component.css'
})
export class ChannelFormComponent {

  channelService = inject(ChannelService);
  channel: Channel = new Channel();

  channelNameErrorMessage!: string;
  successMessage!: string;

  constructor() {

  }

  postChannel() {
    console.log(this.channel);

    this.channelService.post(this.channel).subscribe({
      next: (response) => {
        this.channel = new Channel();
        this.channelNameErrorMessage = "";
        this.successMessage = "Canal publicado!";
      },
      error: (response) => {
        this.channelNameErrorMessage = response.error.name + '!';
      }
    })
  }
}
