package com.innowise.cadenseapp.activity;


import com.innowise.cadenseapp.context.ApplicationContextUtils;
import com.innowise.cadenseapp.dto.weatherDto.WeatherResponse;
import com.innowise.cadenseapp.entity.Weather;
import com.innowise.cadenseapp.service.WeatherService;
import org.springframework.context.ApplicationContext;

public class StoreWeatherActivityImpl implements StoreWeatherActivity {

    @Override
    public void store(WeatherResponse forecast) {
        Weather newWeather=new Weather();

        newWeather.setTemperature(forecast.getMain().getTemp());
        newWeather.setNameOfCity(forecast.getName());

        ApplicationContext appCtx = ApplicationContextUtils.getApplicationContext();
        WeatherService weatherService = appCtx.getBean(WeatherService.class);

        weatherService.save(newWeather);
    }
}
