package com.example.rentcar.user.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserCreateDto extends UserBaseDto {

    private String password;
}
