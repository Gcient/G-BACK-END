package com.simple.gbackend.domain.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageLoginUser {

    private String userAccount;
    private String nickName;
    private String avatarUrl;
    private Integer isValid;
    private Integer isDeleted;
    private Integer gender;
    private Integer permissionNumber;
    private Date createTime;
    private Date updateTime;
}
