package com.example.rentcar.rentalCar.dto;

import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public class RentalResponseDto extends RentalBaseDto {
    private UUID userId;
    private UUID carId;
}
