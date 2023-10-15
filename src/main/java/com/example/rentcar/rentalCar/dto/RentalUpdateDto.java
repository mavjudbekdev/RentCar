package com.example.rentcar.rentalCar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class RentalUpdateDto extends RentalBaseDto {
    private UUID userId;
    private UUID carId;
}
