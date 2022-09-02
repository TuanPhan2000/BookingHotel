package com.example.bookinghotel.dto;


import com.example.bookinghotel.validator.PasswordMatches;
import com.example.bookinghotel.validator.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class UserDto {

    private String name;

    @ValidEmail
    private String email;

    private String password;

    private String confirmPassword;
}
