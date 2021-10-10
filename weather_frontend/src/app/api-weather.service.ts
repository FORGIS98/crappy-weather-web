import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import { Weather } from './weather';

@Injectable({
  providedIn: 'root',
})
export class ApiWeatherService {
  private base_url = 'http://localhost:8080/weather';

  constructor(private http: HttpClient) {}

  getWeather(city: string): Observable<Weather> {
    const url = `${this.base_url}?city=${city}`;
    return this.http
      .get<Weather>(url)
      .pipe(catchError(this.handleError<Weather>(`Weather city=${city}`)));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
