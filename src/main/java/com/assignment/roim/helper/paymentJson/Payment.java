package com.assignment.roim.helper.paymentJson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    private String merchantRefNum;

    private String customerId;

    private Long amount;

    private String currencyCode = "USD";

    private String paymentHandleToken;
}
