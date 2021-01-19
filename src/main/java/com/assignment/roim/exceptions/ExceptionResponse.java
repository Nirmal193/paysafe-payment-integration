package com.assignment.roim.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private String details;
    private Date timeStamp;
}
