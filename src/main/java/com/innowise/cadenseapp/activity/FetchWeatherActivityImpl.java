package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.Root;
import com.innowise.cadenseapp.properties.PropertiesLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

public class FetchWeatherActivityImpl implements FetchWeatherActivity {
    @Override
    public Root fetchWeather() {
        try {
            Properties conf = PropertiesLoader.loadProperties();
            String appid = conf.getProperty("appid");
            String city = conf.getProperty("city");
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appid + "&units=metric";
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Root> response=restTemplate.getForEntity(url, Root.class);

            return response.getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

