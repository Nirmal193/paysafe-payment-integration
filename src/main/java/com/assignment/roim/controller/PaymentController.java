package com.assignment.roim.controller;

import com.assignment.roim.DTO.PaymentDto;
import com.assignment.roim.DTO.ResponseDto;
import com.assignment.roim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class PaymentController {
    @Autowired
    private UserService userService;
    @PostMapping("/payment")
    public ResponseEntity payment(@Valid @RequestBody PaymentDto paymentDto){
        String status = userService.completePayment(paymentDto);
        return new ResponseEntity(new ResponseDto(HttpStatus.OK,status),HttpStatus.OK);
    }
}
