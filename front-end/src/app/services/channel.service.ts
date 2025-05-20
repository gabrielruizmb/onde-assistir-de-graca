import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  http = inject(HttpClient);
  baseUrl = "localhost:8080/api/channels"

  constructor() { }
}
