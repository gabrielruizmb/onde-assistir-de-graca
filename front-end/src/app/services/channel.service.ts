import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Channel } from '../models/channel';
import { UserService } from './user.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  http = inject(HttpClient);
  baseUrl = environment.SERVER + "/api/channels";

  userService = inject(UserService);

  constructor() { }

  getAll(): Observable<Channel[]> {
    return this.http.get<Channel[]>(this.baseUrl);
  }

  getById(id: string): Observable<Channel> {
    return this.http.get<Channel>(this.baseUrl + '/' + id,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  post(channel: Channel): Observable<any> {
    return this.http.post<any>(this.baseUrl, channel,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  put(channel: Channel): Observable<any> {
    return this.http.put<any>(this.baseUrl + '/' + channel.id, channel,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  deleteById(id: string): Observable<Channel> {
    return this.http.delete<Channel>(this.baseUrl + '/' + id,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }
}
