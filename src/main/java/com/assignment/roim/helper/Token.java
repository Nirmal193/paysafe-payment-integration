package com.assignment.roim.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    private String merchantRefNum;
    private String[] paymentTypes = {"CARD"};
}
