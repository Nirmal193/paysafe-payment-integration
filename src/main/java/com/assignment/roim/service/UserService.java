package com.assignment.roim.service;

import com.assignment.roim.DTO.PaymentDto;
import com.assignment.roim.DTO.UserDto;
import com.assignment.roim.helper.ResponseToken;
import com.assignment.roim.helper.ResponseUser;
import com.assignment.roim.helper.Token;
import com.assignment.roim.helper.UserHelper;
import com.assignment.roim.helper.paymentJson.Payment;
import com.assignment.roim.helper.paymentJson.ResponsePayment;
import com.assignment.roim.model.User;
import com.assignment.roim.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpHeaders httpHeader;

    public String registerUser(UserDto userDto){
        User user = userRepository.findByEmail(userDto.getEmail());

        if(user == null) createUser(new User(),userDto);

        String str = createSingleUserToken(userRepository.findByEmail(userDto.getEmail()));

        return str;

    }
    private void createUser(User user,UserDto userDto){
        String Url = "https://api.test.paysafe.com/paymenthub/v1/customers";
        UserHelper userJSON = new UserHelper();
        userJSON.setEmail(userDto.getEmail());
        userJSON.setMerchantCustomerId(userDto.getEmail() + UUID.randomUUID());
        HttpEntity<UserHelper> entity = new HttpEntity<>(userJSON,httpHeader);
        ResponseUser responseUser = (ResponseUser) restTemplate.postForObject(Url,entity,ResponseUser.class);
        user.setEmail(userDto.getEmail());
        user.setUserId(responseUser.getId());
        userRepository.save(user);
    }
    private String createSingleUserToken(User user){
        String Url = "https://api.test.paysafe.com/paymenthub/v1/customers/"+user.getUserId()+"/singleusecustomertokens";
        System.out.println(Url);
        Token tokenJSON = new Token();
        tokenJSON.setMerchantRefNum(""+UUID.randomUUID());

        HttpEntity<Token> entity = new HttpEntity<Token>(tokenJSON,httpHeader);
        ResponseToken responseTokenJSON = (ResponseToken) restTemplate.postForObject(
                Url,  entity, ResponseToken.class);
        return responseTokenJSON.getSingleUseCustomerToken();

    }
    public String completePayment(PaymentDto paymentDto){
        String Url = "https://api.test.paysafe.com/paymenthub/v1/payments";
        Payment paymentJSON = new Payment();


        if (paymentDto.getUserOperation() != null && paymentDto.getUserOperation().equals("ADD")) {
            User user = userRepository.findByEmail(paymentDto.getEmail());

            user.setCustomerOperation("ADD");
            paymentJSON.setCustomerId(userRepository.findByEmail(paymentDto.getEmail()).getUserId());
            userRepository.save(user);
        }


        paymentJSON.setPaymentHandleToken(paymentDto.getPaymentHandleToken());
        paymentJSON.setAmount(paymentDto.getAmount());
        paymentJSON.setMerchantRefNum(UUID.randomUUID() + "");
        HttpEntity<Payment> entity = new HttpEntity<Payment>(paymentJSON, httpHeader);

        try {
            ResponsePayment responsePaymentJSON = (ResponsePayment) restTemplate.postForObject(
                    Url, entity, ResponsePayment.class);
            if (responsePaymentJSON.getStatus().equals("COMPLETED")) return responsePaymentJSON.getStatus();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.BAD_REQUEST) {
                return null;
            }
        }
        return null;
    }
}
