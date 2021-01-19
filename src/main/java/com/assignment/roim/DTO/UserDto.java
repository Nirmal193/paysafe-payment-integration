package com.assignment.roim.DTO;

import com.assignment.roim.validator.ValidEmail;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {
    @NotEmpty
    @Size(min = 6)
    @ValidEmail
    private String email;
}
