package com.innowise.cadenseapp.service;

import com.innowise.cadenseapp.entity.Weather;
import com.innowise.cadenseapp.repo.WeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherService extends CommonServiceImpl<Weather, WeatherRepository>{

    public WeatherService(WeatherRepository repo) {
        super(repo);
    }
}
