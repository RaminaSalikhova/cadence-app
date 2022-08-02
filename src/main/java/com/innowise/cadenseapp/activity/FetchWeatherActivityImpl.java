package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.CityResponse;
import com.innowise.cadenseapp.dto.weatherDto.WeatherResponse;
import com.innowise.cadenseapp.exception.PropertyLoadException;
import com.innowise.cadenseapp.properties.PropertiesLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class FetchWeatherActivityImpl implements FetchWeatherActivity {
    @Override
    public WeatherResponse fetchWeather(String city) {

        try {
            Properties conf = PropertiesLoader.loadProperties();

            String localAppid = conf.getProperty("localAppid");
            String openWeatherAppid = conf.getProperty("openWeatherAppid");

            RestTemplate restTemplate = new RestTemplate();

            String findCityUrl = "https://openweathermap.org/data/2.5/find?q=" + city + "&appid="+openWeatherAppid;
            ResponseEntity<CityResponse> cityResponse = restTemplate.getForEntity(findCityUrl, CityResponse.class);

            Optional<CityResponse> body = Optional.ofNullable(cityResponse.getBody());

            if (body.isPresent()) {
                if (body.get().getCount() == 0) {
                    return null;
                }
            }

            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + localAppid + "&units=metric";
            ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

            return response.getBody();
        } catch (IOException e) {
            throw new PropertyLoadException("Load properties exception",e);
        }
    }
}

