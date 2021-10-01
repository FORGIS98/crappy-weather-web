package com.forgis.weather;

import com.google.gson.Gson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller extends WeatherFormat {

    private final String OPENWEATHER_API_KEY = System.getenv("OPEN_WEATHER");
    private final String url = "https://api.openweathermap.org/data/2.5/weather?units=metric";

    @GetMapping("/weather")
    JSONObject getWeather(@RequestParam(name = "city", required = true) String city) {

        String query = url;
        query += "&q=" + city;
        query += "&appid=" + OPENWEATHER_API_KEY;

        Weather wth = create_weather(query);
        String jsonInString = new Gson().toJson(wth);
        JSONParser jParser = new JSONParser();
        JSONObject jObject = new JSONObject();

        try {
            jObject = (JSONObject) jParser.parse(jsonInString);
        } catch (ParseException parseEx) {
            parseEx.getStackTrace();
        }

        return jObject;
    }
}
