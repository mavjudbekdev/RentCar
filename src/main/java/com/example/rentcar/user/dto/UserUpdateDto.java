package com.example.rentcar.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserUpdateDto extends UserBaseDto {

    private String password;
}
