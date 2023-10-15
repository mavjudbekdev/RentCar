package com.example.rentcar.rentalCar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalBaseDto {

    private LocalDateTime rentalStartTime;
    private LocalDateTime rentalEndTime;
    private double rentalPrice;
}
