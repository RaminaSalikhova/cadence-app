package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.Root;
import com.innowise.cadenseapp.properties.PropertiesLoader;
import com.uber.cadence.BadRequestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

public class FetchWeatherActivityImpl implements FetchWeatherActivity {
    @Override
    public Root fetchWeather(String city) throws IOException, BadRequestError {

        Properties conf = PropertiesLoader.loadProperties();
        String appid = conf.getProperty("appid");
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + appid + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Root> response = restTemplate.getForEntity(url, Root.class);
        if (response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new BadRequestError();
        }
        return response.getBody();

    }
}

