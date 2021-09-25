package com.forgis.weather;

import java.net.http.HttpResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller extends WeatherFormat {

    private final String OPENWEATHER_API_KEY = System.getenv("OPEN_WEATHER");
    private String url = "https://api.openweathermap.org/data/2.5/weather?units=metric";

    @GetMapping("/weather")
    Weather getWeather(@RequestParam(name = "city", required = true) String city,
            @RequestParam(name = "country", required = false) String country) {

        url += "&q=" + city;
        if (country != null) {
            url += "," + country;
        }
        url += "&appid=" + OPENWEATHER_API_KEY;

        HttpResponse<String> response = call_api(url);
        return create_weather(response);
    }
}
