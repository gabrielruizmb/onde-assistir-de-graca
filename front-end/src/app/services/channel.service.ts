import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Channel } from '../models/channel';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  http = inject(HttpClient);
  baseUrl = "http://localhost:8080/api/channels"

  constructor() { }

  getAll(): Observable<Channel[]> {
    return this.http.get<Channel[]>(this.baseUrl);
  }
}
