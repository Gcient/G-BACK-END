package com.simple.gbackend.domain.request;


import lombok.Data;

@Data
public class UserRequest {
    private String userAccount;
    private String userPassword;
}
