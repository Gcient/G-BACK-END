package com.simple.gbackend.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageAddUser {
    private String userAccount;
    private String nickName;
    private String userPassword;
    private Integer gender;
}
