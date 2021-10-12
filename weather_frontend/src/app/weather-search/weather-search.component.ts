import { Component, OnInit } from '@angular/core';

import { Weather, WError } from '../weather';
import { ApiWeatherService } from '../api-weather.service';

@Component({
  selector: 'app-weather-search',
  templateUrl: './weather-search.component.html',
  styleUrls: ['./weather-search.component.css'],
})
export class WeatherSearchComponent implements OnInit {
  weather?: Weather;
  error?: WError;

  constructor(private apiWeatherService: ApiWeatherService) {}

  ngOnInit(): void {}

  search(city: string) {
    city = city.trim();

    // If we call a second time the method,
    // error is not undefined anymore, so,
    // we change its value to undefined.
    this.error = undefined;
    this.apiWeatherService.getWeather(city).subscribe((answer) => {
      if (answer.error) {
        this.error = answer as WError;
      } else {
        this.weather = answer as Weather;
      }
    });
  }
}
