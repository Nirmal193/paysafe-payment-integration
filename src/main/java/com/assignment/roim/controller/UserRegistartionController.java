package com.assignment.roim.controller;

import com.assignment.roim.DTO.ResponseDto;
import com.assignment.roim.DTO.UserDto;
import com.assignment.roim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin("*")
@Controller
public class UserRegistartionController {
    @Autowired
    private UserService userService;
    @PostMapping("/customer/register")
    public ResponseEntity registerCustomer(@Valid @RequestBody UserDto userDto){

        String singleUseCustomerToken = userService.registerUser(userDto);

        System.out.println(singleUseCustomerToken);

//        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
//        URI location = builder.buildAndExpand(userDto.getEmail(),singleUseCustomerToken).toUri();
//        return ResponseEntity.created(location).build();
        System.out.println(singleUseCustomerToken);
        return new ResponseEntity<>(singleUseCustomerToken,HttpStatus.OK);
    }

}
