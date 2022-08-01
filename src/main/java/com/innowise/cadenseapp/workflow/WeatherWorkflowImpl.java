package com.innowise.cadenseapp.workflow;

import com.innowise.cadenseapp.activity.FetchWeatherActivity;
import com.innowise.cadenseapp.activity.StoreWeatherActivity;
import com.innowise.cadenseapp.dto.weatherDto.Root;
import com.uber.cadence.BadRequestError;
import com.uber.cadence.workflow.Workflow;

import java.io.IOException;

public class WeatherWorkflowImpl implements WeatherWorkflow{

    private final FetchWeatherActivity fetchWeatherActivity = Workflow.newActivityStub(FetchWeatherActivity.class);

    private final StoreWeatherActivity storeWeatherActivity=Workflow.newActivityStub(StoreWeatherActivity.class);
    private boolean active = true;

    @Override
    public void getAndStoreWeather(String city) throws IOException, BadRequestError {
        // start(::getAndStoreWeather) -> returns wfId
        // signal
        // action
        // await?
        while (active) {
            Root forecast= fetchWeatherActivity.fetchWeather(city);
            storeWeatherActivity.store(forecast);
            Workflow.await(() -> !active);
        }
    }

    @Override
    public void terminate() {
        this.active = false;
    }
}
