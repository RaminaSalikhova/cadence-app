package com.innowise.cadenseapp.dto.weatherDto;

import lombok.Data;

import java.util.List;

@Data
public class CityResponse {
    private String message;
    private int cod;
    private int count;
    private List<WeatherResponse> list;
}
