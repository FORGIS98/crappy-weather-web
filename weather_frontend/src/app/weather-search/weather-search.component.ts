import { Component, OnInit } from '@angular/core';

import { Weather } from '../weather';
import { ApiWeatherService } from '../api-weather.service';

@Component({
  selector: 'app-weather-search',
  templateUrl: './weather-search.component.html',
  styleUrls: ['./weather-search.component.css']
})
export class WeatherSearchComponent implements OnInit {

  weather?: Weather;

  constructor(private apiWeatherService: ApiWeatherService) { }

  ngOnInit(): void {
  }

  search(city: string) {
    city = city.trim();
    this.apiWeatherService.getWeather(city).subscribe(weather => {
      this.weather = weather;
    });
  }

}
