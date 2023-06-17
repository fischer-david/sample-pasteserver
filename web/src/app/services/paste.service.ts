import { Injectable } from '@angular/core';
import {map, Observable} from "rxjs";
import {Paste} from "../models/paste.model";
import {HttpClient} from "@angular/common/http";
import * as highlightjs from "highlight.js";

@Injectable({
  providedIn: 'root'
})
export class PasteService {

  constructor(private httpClient: HttpClient) { }

  public getPaste(uniqueId: string): Observable<Paste> {
    return this.httpClient.get<Paste>(`/api/paste?uniqueId=${uniqueId}`).pipe(map(x => {
      x.value = highlightjs.default.highlightAuto(x.value as string).value
      return x
    }));
  }

  public createPaste(value: string): Observable<string> {
    return this.httpClient.post<string>(`/api/paste`, value);
  }
}
