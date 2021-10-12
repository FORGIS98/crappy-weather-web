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

        if (city.indexOf(' ') != -1) {
            JSONObject jObject = new JSONObject();
            jObject.put("error",
                    "Currently the web does not support multi-word cities. (Such as New York, Los Angeles, Saint Louis).");
            jObject.put("status", "400");
            return jObject;
        } else {
            String query = url;
            query += "&q=" + city;
            query += "&appid=" + OPENWEATHER_API_KEY;

            JSONParser jParser = new JSONParser();
            JSONObject jObject = new JSONObject();
            try {
                Weather wth = create_weather(query);
                String jsonInString = new Gson().toJson(wth);
                jObject = (JSONObject) jParser.parse(jsonInString);
            } catch (ParseException parseEx) {
                parseEx.getStackTrace();
            } catch (NotFoundException notFoundEx) {
                jObject.put("error", notFoundEx.message);
                jObject.put("status", notFoundEx.code);
            }

            return jObject;
        }
    }
}
