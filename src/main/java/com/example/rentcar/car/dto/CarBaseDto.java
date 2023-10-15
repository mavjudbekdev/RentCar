package com.example.rentcar.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBaseDto {

    private String model;
    private int year;
    private String color;
    private String carNumber;
    private Boolean isSpoiled;
    private double dailRentalPrice;
}
