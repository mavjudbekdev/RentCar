package com.example.rentcar.car.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CarUpdateDto extends CarBaseDto {
    private String name;
}
