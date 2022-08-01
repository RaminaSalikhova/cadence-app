package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.CityResponse;
import com.innowise.cadenseapp.dto.weatherDto.WeatherResponse;
import com.innowise.cadenseapp.properties.PropertiesLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class FetchWeatherActivityImpl implements FetchWeatherActivity {
    @Override
    public WeatherResponse fetchWeather(String city)  {

        Properties conf = null;
        try {
            conf = PropertiesLoader.loadProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String appid = conf.getProperty("appid");

        RestTemplate restTemplate = new RestTemplate();

        String findCityUrl = "https://openweathermap.org/data/2.5/find?q="+ city+"&appid=439d4b804bc8187953eb36d2a8c26a02&_=1659374597310";
        ResponseEntity<CityResponse> cityResponse = restTemplate.getForEntity(findCityUrl, CityResponse.class);
        Optional<CityResponse> body = Optional.ofNullable(cityResponse.getBody());
        if (body.isPresent()) {
            if (body.get().getCount() == 0) {
                return null;
            }
        }
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appid + "&units=metric";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

        return response.getBody();
    }
}

