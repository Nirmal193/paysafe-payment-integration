package com.assignment.roim.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private Long amount;

    private String email;

    private String paymentHandleToken;

    private String userOperation;

}
