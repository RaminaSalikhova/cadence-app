package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.WeatherResponse;
import com.uber.cadence.activity.ActivityMethod;

public interface StoreWeatherActivity {

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 100)
    void store(WeatherResponse weather);
}
