package com.example.rentcar.car.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CarResponseDto extends CarBaseDto {
    private UUID id;
}
