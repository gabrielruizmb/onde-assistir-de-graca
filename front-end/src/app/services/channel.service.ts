import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Channel } from '../models/channel';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  http = inject(HttpClient);
  baseUrl = "http://localhost:8080/api/channels";

  userService = inject(UserService);

  constructor() { }

  getAll(): Observable<Channel[]> {
    return this.http.get<Channel[]>(this.baseUrl);
  }

  post(channel: Channel): Observable<any> {
    return this.http.post<any>(this.baseUrl, channel,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});

  }
}
