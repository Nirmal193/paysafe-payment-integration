package com.assignment.roim.helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseUser {
    private String id;
    private String email;
    private String merchantCustomerId;
}
