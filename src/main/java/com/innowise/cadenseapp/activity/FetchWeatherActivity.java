package com.innowise.cadenseapp.activity;

import com.innowise.cadenseapp.dto.weatherDto.Root;
import com.uber.cadence.BadRequestError;
import com.uber.cadence.activity.ActivityMethod;

import java.io.IOException;

public interface FetchWeatherActivity {

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 100)
    Root fetchWeather(String city) throws IOException, BadRequestError;
}
