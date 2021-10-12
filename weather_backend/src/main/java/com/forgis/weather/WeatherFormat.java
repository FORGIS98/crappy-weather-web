package com.forgis.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherFormat {

    public Weather create_weather(String url) throws NotFoundException {
        HttpResponse<String> response = call_api(url);

        Weather weather;
        try {
            JSONObject jObject = new JSONObject();
            JSONParser jParser = new JSONParser();
            jObject = (JSONObject) jParser.parse(response.body());

            if (jObject.get("cod").toString().equals("200")) {
                String description = getDescription((JSONArray) jObject.get("weather"));
                String city = jObject.get("name").toString();
                String country = getCountryCode((JSONObject) jObject.get("sys"));
                String weather_icon = getIcon((JSONArray) jObject.get("weather"));
                HashMap<String, Float> temperature = getTemperature((JSONObject) jObject.get("main"));

                weather = new Weather(description, city, country, weather_icon, temperature);
            } else {
                throw new NotFoundException((String) jObject.get("cod"), (String) jObject.get("message"));
            }
        } catch (ParseException parserEx) {
            weather = new Weather();
            parserEx.getStackTrace();
        } catch (NumberFormatException numberEx) {
            weather = new Weather();
            numberEx.getStackTrace();
        }

        return weather;
    }

    private HttpResponse<String> call_api(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException ioEx) {
            ioEx.getStackTrace();
        } catch (InterruptedException interrEx) {
            interrEx.getStackTrace();
        }

        return response;
    }

    private String getDescription(JSONArray jArray) {
        JSONObject jObject = new JSONObject();
        jObject = (JSONObject) jArray.get(0);
        return jObject.get("description").toString();
    }

    private String getIcon(JSONArray jArray) {
        JSONObject jObject = new JSONObject();
        jObject = (JSONObject) jArray.get(0);
        return jObject.get("icon").toString();
    }

    private HashMap<String, Float> getTemperature(JSONObject jObject) throws NumberFormatException {
        HashMap<String, Float> temp = new HashMap<String, Float>();
        temp.put("temp", Float.parseFloat(jObject.get("temp").toString()));
        temp.put("min", Float.parseFloat(jObject.get("temp_min").toString()));
        temp.put("max", Float.parseFloat(jObject.get("temp_max").toString()));
        return temp;
    }

    private String getCountryCode(JSONObject jObject) {
        return jObject.get("country").toString();
    }
}
