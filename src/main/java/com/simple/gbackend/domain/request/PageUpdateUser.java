package com.simple.gbackend.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUpdateUser {
    private String userAccount;
    private String nickName;
    private Integer gender;
    private Integer isValid;
    private Integer permissionNumber;
}
