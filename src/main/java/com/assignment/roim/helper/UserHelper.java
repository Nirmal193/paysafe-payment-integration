package com.assignment.roim.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHelper {
    private String merchantCustomerId;
    private DateOfBirth dateOfBirth = new DateOfBirth();
    private String locale = "en_US";
    private String email;
}
