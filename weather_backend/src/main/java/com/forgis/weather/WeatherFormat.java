package com.forgis.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherFormat {
    public HttpResponse<String> call_api(String url) {
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

    public Weather create_weather(HttpResponse<String> response) {
        try {
            JSONObject jObject = new JSONObject();
            JSONParser jParser = new JSONParser();
            jObject = (JSONObject) jParser.parse(response.body());
            jObject.get("weather");
        } catch (ParseException parserEx) {
            parserEx.getStackTrace();
        }
        return null;
    }
}
