package com.forgis.weather;

import java.util.HashMap;

public class Weather {
    String description, city_name, country_code;
    HashMap<String, Float> temperature;
    float rain, snow;

    public Weather(String description, String city_name, String countre_code, HashMap<String, Float> temperature) {
        this.description = description;
        this.city_name = city_name;
        this.country_code = countre_code;
        this.temperature = temperature;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public void setSnow(float snow) {
        this.snow = snow;
    }
}
