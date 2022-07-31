package com.innowise.cadenseapp.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GetWeatherDto {
    Timestamp timeOfRecordCreation;
    String nameOfCity;
    Float temperature;
}
