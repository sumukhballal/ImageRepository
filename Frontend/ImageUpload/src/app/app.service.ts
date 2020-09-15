import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

export interface Image {
   id : number;
   name : string;
   type : string;
   date : Date;
    url : string;
}

@Injectable()
export class AppService {
    constructor(private httpClient: HttpClient) { }

    getAllImages(url) {
     return this.httpClient.get<Image[]>(url);
    }
}
