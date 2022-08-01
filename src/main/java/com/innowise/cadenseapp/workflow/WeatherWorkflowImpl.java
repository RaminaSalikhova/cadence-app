package com.innowise.cadenseapp.workflow;

import com.innowise.cadenseapp.activity.FetchWeatherActivity;
import com.innowise.cadenseapp.activity.StoreWeatherActivity;
import com.innowise.cadenseapp.dto.weatherDto.WeatherResponse;
import com.uber.cadence.workflow.Workflow;

import java.util.Optional;

public class WeatherWorkflowImpl implements WeatherWorkflow {

    private final FetchWeatherActivity fetchWeatherActivity = Workflow.newActivityStub(FetchWeatherActivity.class);

    private final StoreWeatherActivity storeWeatherActivity = Workflow.newActivityStub(StoreWeatherActivity.class);
    private boolean active = true;

    @Override
    public void getAndStoreWeather(String city) {
        while (active) {
            Optional<WeatherResponse> forecast = Optional.ofNullable(fetchWeatherActivity.fetchWeather(city));
            forecast.ifPresent((frst) -> storeWeatherActivity.store(frst));
            Workflow.await(() -> !active);
        }
    }

    @Override
    public void terminate() {
        this.active = false;
    }
}
