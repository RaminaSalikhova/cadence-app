package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.Root;
import com.uber.cadence.activity.ActivityMethod;

public interface FetchWeatherActivity {

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 100)
    Root fetchWeather();
}
