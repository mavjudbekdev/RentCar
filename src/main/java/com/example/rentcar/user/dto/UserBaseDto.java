package com.example.rentcar.user.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDto {
    @Size(min = 3, max = 20)
    private String name;
    private String surname;
    private String phoneNumber;
    private String passportNumber;
}
